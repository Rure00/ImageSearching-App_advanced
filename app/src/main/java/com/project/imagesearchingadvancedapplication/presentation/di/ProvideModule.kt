package com.project.imagesearchingadvancedapplication.presentation.di

import com.project.imagesearchingadvancedapplication.data.repository.AppRepository
import com.project.imagesearchingadvancedapplication.data.repository.AppRepositoryImpl
import com.project.imagesearchingadvancedapplication.data.source.retrofit.RetrofitClient
import com.project.imagesearchingadvancedapplication.data.source.retrofit.RetrofitService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object ProvideModule {
    @Provides
    @ViewModelScoped
    fun provideRetrofitService() : RetrofitService {
        return RetrofitClient.getInstance().create(RetrofitService::class.java)
    }
}
