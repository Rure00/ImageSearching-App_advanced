package com.project.imagesearchingadvancedapplication.data.repository

import com.project.imagesearchingadvancedapplication.data.source.LocalDataSource
import com.project.imagesearchingadvancedapplication.data.source.RemoteDataSource
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): AppRepository {

}