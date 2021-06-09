package com.chabi.android.chabiapp.ui.questionnaire

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.chabi.android.chabiapp.R
import com.chabi.android.chabiapp.databinding.ActivityQuestionnaireBinding
import com.chabi.android.chabiapp.utils.Constant
import com.chabi.android.chabiapp.viewmodel.ViewModelFactory
import com.febian.android.lib_task_api.Result
import com.febian.android.lib_task_api.TextClassificationClient

class QuestionnaireActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionnaireBinding
    private lateinit var viewModel: QuestionnaireViewModel
    private var answeredQuestion = 0

    private lateinit var client: TextClassificationClient
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionnaireBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val questionType = intent.getStringExtra(Constant.QUESTION_TYPE)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(
            this,
            factory
        )[QuestionnaireViewModel::class.java]

        questionType?.let { viewModel.setQuestionBank(it) }

        try {
            updateQuestion()
        } catch (e: Exception) {
            Toast.makeText(this, "Can't load question data", Toast.LENGTH_SHORT).show()
        }

        binding.btnPrevious.setOnClickListener {
            viewModel.moveToPrevious()
            binding.rgOptions.clearCheck()
            updateQuestion()
        }

        binding.btnNext.setOnClickListener {
            viewModel.moveToNext()
            binding.rgOptions.clearCheck()
            updateQuestion()
        }

        binding.btnBack.setOnClickListener {
            showQuitAlertDialog()
        }

        client = TextClassificationClient(applicationContext)
        handler = Handler(Looper.getMainLooper())
    }

    private fun updateQuestion() {
        binding.tvQuestionCount.text = String.format(
            resources.getString(R.string.quiz_question_count),
            viewModel.currentIndex + 1,
            viewModel.getQuestionBankSize()
        )

        binding.tvQuestion.text = viewModel.currentQuestionText
        binding.rbOptionA.text = viewModel.currentQuestionOptionAText
        binding.rbOptionB.text = viewModel.currentQuestionOptionBText

        if (viewModel.currentQuestionIsAnswered!!) {
            when (viewModel.currentQuestionUserAnswer) {
                viewModel.currentQuestionOptionAValue -> binding.rbOptionA.isChecked = true
                viewModel.currentQuestionOptionBValue -> binding.rbOptionB.isChecked = true
            }
        }
    }

    private fun updateProgressBar() {
        if (viewModel.currentQuestionIsAnswered == false) {
            answeredQuestion += 1
        }

        val quizSize = viewModel.getQuestionBankSize()
        binding.pbCountProgress.progress = ((answeredQuestion * 100) / quizSize)

        if (answeredQuestion == quizSize) {
            binding.btnSubmit.visibility = View.VISIBLE
            binding.btnSubmit.setOnClickListener {
                classifyUserAnswer()
//                Toast.makeText(this, "Proses ML, intent ke main activity", Toast.LENGTH_SHORT)
//                    .show()
//
//                val intent = Intent(this@QuestionnaireActivity, MainActivity::class.java)
//                startActivity(intent)
            }
        }
    }

    private fun classifyUserAnswer() {
        val userAnswers = viewModel.getUserAnswers().joinToString()
        classify(userAnswers)
    }

    private fun classify(text: String) {
        handler.post {
            // Run text classification with TF Lite.
            val results: List<Result> = client.classify(text)

            // Save classification result
            setResult(text, results)
        }
    }

    private fun setResult(inputText: String, results: List<Result>) {
        // Run on UI thread as we'll updating our app UI
        runOnUiThread {
            var textToShow: String? = "Input: $inputText\nOutput:\n"
            for (i in results.indices) {
                val result: Result = results[i]
                textToShow += java.lang.String.format(
                    "    %s: %s\n",
                    result.title,
                    result.confidence
                )
            }
            textToShow += "---------\n"

            Log.d("cek hasil", textToShow.toString())
        }
    }

    fun onRadioButtonClicked(v: View) {
        when (v.id) {
            R.id.rb_option_a -> {
                updateProgressBar()
                viewModel.setCurrentQuestionUserAnswer(viewModel.currentQuestionOptionAValue)
                viewModel.setCurrentQuestionIsAnswered(true)
            }
            R.id.rb_option_b -> {
                updateProgressBar()
                viewModel.setCurrentQuestionUserAnswer(viewModel.currentQuestionOptionBValue)
                viewModel.setCurrentQuestionIsAnswered(true)
            }
        }
    }

    override fun onBackPressed() {
        showQuitAlertDialog()
    }

    private fun showQuitAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.confirmation))
        builder.setMessage(getString(R.string.confirmation_message))
        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            this@QuestionnaireActivity.finish()
        }
        builder.setNegativeButton(getString(R.string.cancel)) { _, _ ->
        }
        builder.show()
    }

    override fun onStart() {
        super.onStart()
        handler.post { client.load() }
    }

    override fun onStop() {
        super.onStop()
        handler.post { client.unload() }
    }
}