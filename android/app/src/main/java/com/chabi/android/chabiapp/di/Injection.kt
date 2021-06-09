package com.chabi.android.chabiapp.di

import com.chabi.android.chabiapp.data.source.AppRepository
import com.chabi.android.chabiapp.data.source.local.LocalDataSource

object Injection {

    fun provideRepository(): AppRepository {
        val localDataSource = LocalDataSource.getInstance()

        return AppRepository.getInstance(localDataSource)
    }
}