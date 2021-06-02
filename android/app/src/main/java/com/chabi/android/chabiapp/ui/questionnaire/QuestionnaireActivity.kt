package com.chabi.android.chabiapp.ui.questionnaire

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.chabi.android.chabiapp.R
import com.chabi.android.chabiapp.databinding.ActivityQuestionnaireBinding
import com.chabi.android.chabiapp.utils.Constant
import com.chabi.android.chabiapp.viewmodel.ViewModelFactory

class QuestionnaireActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionnaireBinding
    private lateinit var viewModel: QuestionnaireViewModel
    private var answeredQuestion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionnaireBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(
            this,
            factory
        )[QuestionnaireViewModel::class.java]

        viewModel.setQuestionBank(Constant.PRESCHOOL_TYPE)

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
                "a" -> binding.rbOptionA.isChecked = true
                "b" -> binding.rbOptionB.isChecked = true
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
                Toast.makeText(this, "Proses ML, intent ke main activity", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onRadioButtonClicked(v: View) {
        when (v.id) {
            R.id.rb_option_a -> {
                updateProgressBar()
                viewModel.setCurrentQuestionUserAnswer("a")
                viewModel.setCurrentQuestionIsAnswered(true)
            }
            R.id.rb_option_b -> {
                updateProgressBar()
                viewModel.setCurrentQuestionUserAnswer("b")
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
}