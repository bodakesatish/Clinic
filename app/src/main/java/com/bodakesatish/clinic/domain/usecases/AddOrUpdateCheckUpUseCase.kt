package com.bodakesatish.clinic.domain.usecases

import com.bodakesatish.clinic.domain.model.Checkup
import com.bodakesatish.clinic.domain.repository.CheckupRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddOrUpdateCheckUpUseCase @Inject constructor(
    private val checkupRepository: CheckupRepository
)  {
    suspend operator fun invoke(checkUp: Checkup): Long {
       return checkupRepository.insertOrUpdate(checkUp)
    }
}