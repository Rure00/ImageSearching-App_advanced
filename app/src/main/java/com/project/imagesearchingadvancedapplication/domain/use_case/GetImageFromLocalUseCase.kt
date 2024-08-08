package com.project.imagesearchingadvancedapplication.domain.use_case

import com.project.imagesearchingadvancedapplication.data.repository.AppRepository
import com.project.imagesearchingadvancedapplication.domain.model.ImageData
import javax.inject.Inject

class GetImageFromLocalUseCase @Inject constructor(
private val appRepository: AppRepository
) {
    operator fun invoke(): List<ImageData>
        = appRepository.getImageFromLocal()
}