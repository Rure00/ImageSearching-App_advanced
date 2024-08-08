package com.project.imagesearchingadvancedapplication.presentation.di

import com.project.imagesearchingadvancedapplication.data.repository.AppRepository
import com.project.imagesearchingadvancedapplication.data.repository.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
abstract class BindModule {
    @Binds
    @Singleton
    abstract fun provideAppRepository(impl: AppRepositoryImpl): AppRepository
}