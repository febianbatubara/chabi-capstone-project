/*
 * Copyright 2019 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.febian.android.lib_interpreter

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.metadata.MetadataExtractor
import java.io.*
import java.nio.ByteBuffer
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.util.*

/**
 * Interface to load TfLite model and provide predictions.
 */
class TextClassificationClient(private val context: Context) {
    private val dic: MutableMap<String, Int> = HashMap()
    private val labels: MutableList<String> = ArrayList()
    var tflite: Interpreter? = null
        private set

    /**
     * Load the TF Lite model and dictionary so that the client can start classifying text.
     */
    fun load() {
        loadModel()
    }

    /**
     * Load TF Lite model.
     */
    @Synchronized
    private fun loadModel() {
        try {
            // Load the TF Lite model
            val buffer: ByteBuffer = loadModelFile(
                context.assets, MODEL_PATH
            )
            tflite = Interpreter(buffer)
            Log.v(TAG, "TFLite model loaded.")

            // Use metadata extractor to extract the dictionary and label files.
            val metadataExtractor = MetadataExtractor(buffer)

            // Extract and load the dictionary file.
            val dictionaryFile = metadataExtractor.getAssociatedFile("vocab.txt")
            loadDictionaryFile(dictionaryFile)
            Log.v(TAG, "Dictionary loaded.")

            // Extract and load the label file.
            val labelFile = metadataExtractor.getAssociatedFile("labels.txt")
            loadLabelFile(labelFile)
            Log.v(TAG, "Labels loaded.")
        } catch (ex: IOException) {
            Log.e(TAG, "Error loading TF Lite model.\n", ex)
        }
    }

    /**
     * Free up resources as the client is no longer needed.
     */
    @Synchronized
    fun unload() {
        tflite!!.close()
        dic.clear()
        labels.clear()
    }

    /**
     * Classify an input string and returns the classification results.
     */
    @Synchronized
    fun classify(text: String): List<Result> {
        // Pre-prosessing.
        val input = tokenizeInputText(text)

        // Run inference.
        Log.v(TAG, "Classifying text with TF Lite...")
        val output = Array(1) { FloatArray(labels.size) }
        tflite!!.run(input, output)

        // Find the best classifications.
        val pq = PriorityQueue(
            MAX_RESULTS
        ) { lhs: Result, rhs: Result -> (rhs.confidence!!).compareTo(lhs.confidence!!) }
        for (i in labels.indices) {
            pq.add(Result("" + i, labels[i], output[0][i]))
        }
        val results = ArrayList<Result>()
        while (!pq.isEmpty()) {
            results.add(pq.poll())
        }
        Collections.sort(results)
        // Return the probability of each class.
        return results
    }

    /**
     * Load dictionary from model file.
     */
    @Throws(IOException::class)
    private fun loadLabelFile(ins: InputStream) {
        val reader = BufferedReader(InputStreamReader(ins))
        // Each line in the label file is a label.
        while (reader.ready()) {
            labels.add(reader.readLine())
        }
    }

    /**
     * Load labels from model file.
     */
    @Throws(IOException::class)
    private fun loadDictionaryFile(ins: InputStream) {
        val reader = BufferedReader(InputStreamReader(ins))
        // Each line in the dictionary has two columns.
        // First column is a word, and the second is the index of this word.
        while (reader.ready()) {
            val line = Arrays.asList(*reader.readLine().split(" ").toTypedArray())
            if (line.size < 2) {
                continue
            }
            dic[line[0]] = line[1].toInt()
        }
    }

    /**
     * Pre-prosessing: tokenize and map the input words into a float array.
     */
    fun tokenizeInputText(text: String): Array<IntArray> {
        val tmp = IntArray(SENTENCE_LEN)
        val array = Arrays.asList(*text.split(SIMPLE_SPACE_OR_PUNCTUATION).toTypedArray())
        var index = 0
        // Prepend <START> if it is in vocabulary file.
        if (dic.containsKey(START)) {
            tmp[index++] = dic[START]!!
        }
        for (word in array) {
            if (index >= SENTENCE_LEN) {
                break
            }
            tmp[index++] = if (dic.containsKey(word)) dic[word]!! else dic[UNKNOWN] as Int
        }
        // Padding and wrapping.
        Arrays.fill(tmp, index, SENTENCE_LEN - 1, dic[PAD] as Int)
        return arrayOf(tmp)
    }

    fun getDic(): Map<String, Int> {
        return dic
    }

    fun getLabels(): List<String> {
        return labels
    }

    companion object {
        private const val TAG = "Interpreter"
        private const val SENTENCE_LEN = 256 // The maximum length of an input sentence.

        // Simple delimiter to split words.
        private const val SIMPLE_SPACE_OR_PUNCTUATION = " |\\,|\\.|\\!|\\?|\n"
        private const val MODEL_PATH = "text_classification.tflite"

        /*
     * Reserved values in ImdbDataSet dic:
     * dic["<PAD>"] = 0      used for padding
     * dic["<START>"] = 1    mark for the start of a sentence
     * dic["<UNKNOWN>"] = 2  mark for unknown words (OOV)
     */
        private const val START = "<START>"
        private const val PAD = "<PAD>"
        private const val UNKNOWN = "<UNKNOWN>"

        /**
         * Number of results to show in the UI.
         */
        private const val MAX_RESULTS = 3

        /**
         * Load TF Lite model from assets.
         */
        @Throws(IOException::class)
        private fun loadModelFile(assetManager: AssetManager, modelPath: String): MappedByteBuffer {
            assetManager.openFd(modelPath).use { fileDescriptor ->
                FileInputStream(fileDescriptor.fileDescriptor).use { inputStream ->
                    val fileChannel = inputStream.channel
                    val startOffset = fileDescriptor.startOffset
                    val declaredLength = fileDescriptor.declaredLength
                    return fileChannel.map(
                        FileChannel.MapMode.READ_ONLY,
                        startOffset,
                        declaredLength
                    )
                }
            }
        }
    }
}