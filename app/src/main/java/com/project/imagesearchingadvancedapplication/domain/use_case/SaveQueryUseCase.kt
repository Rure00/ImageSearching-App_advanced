package com.project.imagesearchingadvancedapplication.domain.use_case

import com.project.imagesearchingadvancedapplication.data.repository.AppRepository
import javax.inject.Inject

class SaveQueryUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    operator fun invoke(query: String) {
        appRepository.saveLastQuery(query)
    }
}