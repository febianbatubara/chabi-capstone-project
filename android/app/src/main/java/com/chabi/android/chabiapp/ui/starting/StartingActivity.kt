package com.chabi.android.chabiapp.ui.starting

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chabi.android.chabiapp.R
import com.chabi.android.chabiapp.databinding.ActivityStartingBinding
import com.chabi.android.chabiapp.ui.questionnaire.QuestionnaireActivity
import com.chabi.android.chabiapp.utils.Constant
import com.chabi.android.chabiapp.utils.QuestionDataFactory

class StartingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartingBinding
    private var selectedAge = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showQuestion()

        binding.btnSubmit.setOnClickListener {
            if (binding.rgOptions.checkedRadioButtonId == -1) {
                Toast.makeText(
                    this@StartingActivity,
                    getString(R.string.age_range),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val intent = Intent(this@StartingActivity, QuestionnaireActivity::class.java)
                intent.putExtra(Constant.QUESTION_TYPE, selectedAge)
                startActivity(intent)
            }
        }
    }

    private fun showQuestion() {
        val question = QuestionDataFactory.getAgeQuestion()
        binding.tvQuestion.text = question.questionText
        binding.rbOptionA.text = question.optionA.optionText
        binding.rbOptionB.text = question.optionB.optionText
    }

    fun onAgeClicked(v: View) {
        when (v.id) {
            R.id.rb_option_a -> {
                selectedAge = Constant.PRESCHOOL_TYPE
            }
            R.id.rb_option_b -> {
                selectedAge = Constant.SCHOOL_AGED_TYPE
            }
        }
    }
}