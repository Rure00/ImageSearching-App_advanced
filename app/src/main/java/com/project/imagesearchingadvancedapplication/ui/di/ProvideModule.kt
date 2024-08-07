package com.project.imagesearchingadvancedapplication.ui.di

import com.project.imagesearchingadvancedapplication.data.source.retrofit.RetrofitClient
import com.project.imagesearchingadvancedapplication.data.source.retrofit.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object ProvideModule {
    @Provides
    @Singleton
    fun provideRetrofitService() : RetrofitService {
        return RetrofitClient.getInstance().create(RetrofitService::class.java)
    }
}