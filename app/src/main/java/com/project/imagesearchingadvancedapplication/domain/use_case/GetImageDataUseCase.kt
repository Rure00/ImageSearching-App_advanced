package com.project.imagesearchingadvancedapplication.domain.use_case

import com.project.imagesearchingadvancedapplication.data.repository.AppRepository
import com.project.imagesearchingadvancedapplication.domain.model.ImageData
import javax.inject.Inject

class GetImageDataUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    private val size = 30
    suspend operator fun invoke(query: String): List<ImageData> {
        return appRepository.getImageData(query, size)
    }
}