package com.project.imagesearchingadvancedapplication.data.repository

import com.project.imagesearchingadvancedapplication.data.source.LocalDataSource
import com.project.imagesearchingadvancedapplication.data.source.RemoteDataSource
import com.project.imagesearchingadvancedapplication.domain.model.ImageData
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): AppRepository {
    override suspend fun getImageData(query: String, page: Int): List<ImageData> {
        val images = remoteDataSource.getImages(query, page)
        val videos = remoteDataSource.getVideos(query, page)

        return (images + videos).sortedByDescending { it.time }
    }

    override fun saveImageData(list: List<ImageData>) {
        localDataSource.saveImages(list)
    }

    override fun getLastQuery(): String
        = localDataSource.getLastQuery() ?: ""


    override fun saveLastQuery(query: String) {
        localDataSource.saveQuery(query)
    }
}