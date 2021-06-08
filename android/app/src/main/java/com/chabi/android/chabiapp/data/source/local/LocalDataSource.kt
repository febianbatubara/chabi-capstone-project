package com.chabi.android.chabiapp.data.source.local

import com.chabi.android.chabiapp.utils.QuestionDataFactory

class LocalDataSource {

    companion object {
        @Volatile
        private var instance: LocalDataSource? = null

        fun getInstance(): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource().apply { instance = this }
            }
    }

    fun getPreschoolerQuestion() = QuestionDataFactory.generatePreschoolerQuestion()

    fun getSchoolAgedQuestion() = QuestionDataFactory.generateSchoolAgedQuestion()
}