package com.chabi.android.chabiapp.ui.questionnaire

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.chabi.android.chabiapp.R
import com.chabi.android.chabiapp.databinding.ActivityQuestionnaireBinding
import com.chabi.android.chabiapp.ml.ClassifierModel
import com.chabi.android.chabiapp.ui.home.MainActivity
import com.chabi.android.chabiapp.utils.Constant
import com.chabi.android.chabiapp.viewmodel.ViewModelFactory
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer

class QuestionnaireActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionnaireBinding
    private lateinit var viewModel: QuestionnaireViewModel
    private var answeredQuestion = 0

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

        var byteBuffer: ByteBuffer = ByteBuffer.allocateDirect(4*1)
//        byteBuffer.put

        val model = ClassifierModel.newInstance(this)

        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 533), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        model.close()

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
                Toast.makeText(this, "Proses ML, intent ke main activity", Toast.LENGTH_SHORT)
                    .show()

                val intent = Intent(this@QuestionnaireActivity, MainActivity::class.java)
                startActivity(intent)
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