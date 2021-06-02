package com.chabi.android.chabiapp.ui.questionnaire

import androidx.lifecycle.ViewModel
import com.chabi.android.chabiapp.data.source.AppRepository
import com.chabi.android.chabiapp.data.source.local.entity.QuestionEntity
import com.chabi.android.chabiapp.utils.Constant

class QuestionnaireViewModel(private val appRepository: AppRepository) : ViewModel() {

    private lateinit var questionBank: List<QuestionEntity>

    fun getQuestionBank() = questionBank

    fun setQuestionBank(type: String) {
        return if (type == Constant.PRESCHOOL_TYPE) {
            questionBank = appRepository.getPreschoolerQuestion()
        } else {
            questionBank = appRepository.getSchoolAgedQuestion()
        }
    }

    fun getUserAnswers(): List<Any> {
        val listAnswer = questionBank.map { it.userAnswer }
        val listOrder = questionBank.map { it.order }

        return listAnswer.zip(listOrder)
    }

    fun getQuestionBankSize() = questionBank.size

    var currentIndex = 0

    val currentQuestionText: String
        get() = questionBank[currentIndex].questionText

    val currentQuestionOptionAText: String
        get() = questionBank[currentIndex].optionA.optionText

    val currentQuestionOptionBText: String
        get() = questionBank[currentIndex].optionB.optionText

    val currentQuestionIsAnswered: Boolean?
        get() = questionBank[currentIndex].isAnswered

    fun setCurrentQuestionIsAnswered(answered: Boolean) {
        questionBank[currentIndex].isAnswered = answered
    }

    val currentQuestionUserAnswer: String?
        get() = questionBank[currentIndex].userAnswer

    fun setCurrentQuestionUserAnswer(answer: String) {
        questionBank[currentIndex].userAnswer = answer
    }

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {
        currentIndex = (currentIndex - 1)

        if (currentIndex < 0) {
            currentIndex = (currentIndex + questionBank.size)
        }
    }
}