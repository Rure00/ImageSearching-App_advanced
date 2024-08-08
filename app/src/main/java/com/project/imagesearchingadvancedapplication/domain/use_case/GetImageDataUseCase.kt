package com.project.imagesearchingadvancedapplication.domain.use_case

import com.project.imagesearchingadvancedapplication.data.repository.AppRepository
import com.project.imagesearchingadvancedapplication.domain.model.ImageData
import javax.inject.Inject

class GetImageDataUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke(query: String, page: Int): List<ImageData> {
        return appRepository.getImageData(query, page)
    }
}