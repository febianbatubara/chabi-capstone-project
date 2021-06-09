package com.chabi.android.chabiapp.data.source

import androidx.lifecycle.LiveData
import com.chabi.android.chabiapp.data.source.local.entity.QuestionEntity

interface AppDataSource {

    fun getPreschoolerQuestion(): List<QuestionEntity>

    fun getSchoolAgedQuestion(): List<QuestionEntity>
}