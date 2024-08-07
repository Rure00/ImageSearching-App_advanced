package com.project.imagesearchingadvancedapplication.ui.di

import com.project.imagesearchingadvancedapplication.data.api.RetrofitClient
import com.project.imagesearchingadvancedapplication.data.api.RetrofitService
import com.project.imagesearchingadvancedapplication.data.repository.AppRepository
import com.project.imagesearchingadvancedapplication.data.repository.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
abstract class BindModule {
    @Binds
    @Singleton
    abstract fun provideAppRepository(impl: AppRepositoryImpl): AppRepository

}