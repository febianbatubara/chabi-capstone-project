/*
 * Copyright 2020 The TensorFlow Authors. All Rights Reserved.
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
package com.febian.android.lib_task_api

import android.content.Context
import android.util.Log
import org.tensorflow.lite.task.text.nlclassifier.NLClassifier
import java.io.IOException
import java.util.*

/** Load TfLite model and provide predictions with task api.  */
class TextClassificationClient(private val context: Context) {

    var classifier: NLClassifier? = null

    fun load() {
        try {
            classifier = NLClassifier.createFromFile(context, MODEL_PATH)
        } catch (e: IOException) {
            Log.e(TAG, e.message!!)
        }
    }

    fun unload() {
        classifier!!.close()
        classifier = null
    }

    fun classify(text: String?): List<Result> {
        val apiResults = classifier!!.classify(text)
        val results: MutableList<Result> = ArrayList(apiResults.size)
        for (i in apiResults.indices) {
            val category = apiResults[i]
            results.add(Result("" + i, category.label, category.score))
        }
        results.sort()
        return results
    }

    companion object {
        private const val TAG = "TaskApi"
        private const val MODEL_PATH = "model.tflite"
    }
}