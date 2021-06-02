package com.chabi.android.chabiapp.starting

object Question {

    fun getQuestions(): ArrayList<QuestionCategory>{
        val questionList = ArrayList<QuestionCategory>()

        val que = QuestionCategory(
            1,
            "Choose your age range",
            "6-10 years",
            "11-15 years"
        )

        questionList.add(que)

        return questionList
    }
}