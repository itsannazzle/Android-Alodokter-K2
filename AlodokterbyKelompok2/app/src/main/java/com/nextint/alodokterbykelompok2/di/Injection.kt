package com.nextint.alodokterbykelompok2.di

import android.content.Context
import com.nextint.alodokterbykelompok2.data.AloRepository
import com.nextint.alodokterbykelompok2.data.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): AloRepository{
        val remoteDataSource = RemoteDataSource.getInstance()
        return AloRepository.getInstance(remoteDataSource)
    }
}