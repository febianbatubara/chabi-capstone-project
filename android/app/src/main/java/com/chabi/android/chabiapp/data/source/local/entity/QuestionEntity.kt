package com.chabi.android.chabiapp.data.source.local.entity

data class QuestionEntity(
    val questionId: Int,
    val questionText: String,
    val questionValue: String,
    val optionA: OptionEntity,
    val optionB: OptionEntity,
    val order: Int,
    var isAnswered: Boolean? = false,
    var userAnswer: String? = null
)