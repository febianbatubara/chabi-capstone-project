package com.chabi.android.chabiapp.data.source

import com.chabi.android.chabiapp.data.source.local.LocalDataSource
import com.chabi.android.chabiapp.data.source.local.entity.QuestionEntity

class AppRepository private constructor(private val localDataSource: LocalDataSource) :
    AppDataSource {

    companion object {
        @Volatile
        private var instance: AppRepository? = null

        fun getInstance(localData: LocalDataSource): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(localData).apply { instance = this }
            }
    }

    override fun getPreschoolerQuestion(): List<QuestionEntity> =
        localDataSource.getPreschoolerQuestion()

    override fun getSchoolAgedQuestion(): List<QuestionEntity> =
        localDataSource.getSchoolAgedQuestion()
}