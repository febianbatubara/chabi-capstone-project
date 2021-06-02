package com.chabi.android.chabiapp.ui.starting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chabi.android.chabiapp.R
import com.chabi.android.chabiapp.utils.QuestionDataFactory

class StartingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starting)

        val questionsList = QuestionDataFactory.getAgeQuestions()
    }
}