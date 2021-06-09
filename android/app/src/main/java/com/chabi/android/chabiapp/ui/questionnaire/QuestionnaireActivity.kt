package com.chabi.android.chabiapp.ui.questionnaire

import android.content.Context
import android.content.Intent
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
import com.chabi.android.chabiapp.ui.home.MainActivity
import com.chabi.android.chabiapp.utils.Constant
import com.chabi.android.chabiapp.viewmodel.ViewModelFactory
import com.febian.android.lib_task_api.Result
import com.febian.android.lib_task_api.TextClassificationClient
import java.util.*

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
            }
        }
    }

    private fun classifyUserAnswer() {
        val questionList = viewModel.getQuestionList()
        val userAnswers = viewModel.getUserAnswers()

        val combinedValue =
            questionList.zip(userAnswers) { question, answer -> "$question $answer" }
        val textToClassify = combinedValue.joinToString(separator = " ")

        classify(textToClassify)
    }

    private fun classify(text: String) {
        handler.post {
            val results: ArrayList<Result>? = client.classify(text)
            results?.let { setResult(it) }
        }
    }

    private fun setResult(results: List<Result>) {
        var maxIndex = 0
        var maxValue = 0f

        for (i in results.indices) {
            val result: Result = results[i]

            if (result.confidence!! > maxValue) {
                maxValue = result.confidence!!
                maxIndex = i
            }

            val predictions = java.lang.String.format(
                "    %s: %s\n",
                result.title,
                result.confidence
            )
            Log.d(CHECK_TAG, predictions.toString())
        }
        Log.d(CHECK_TAG, "${results[maxIndex].title} ${results[maxIndex].confidence}")

        //save result to shared preference
        val prefs = getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(Constant.USER_PERSONALITY_TYPE_KEY, results[maxIndex].title)
        editor.apply()

        //intent to main
        val intent = Intent(this@QuestionnaireActivity, MainActivity::class.java)
        intent.putExtra(Constant.USER_PERSONALITY_TYPE_KEY, results[maxIndex].title)
        startActivity(intent)
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

    companion object {
        private const val CHECK_TAG = "CHECK_PREDICTION"
    }
}