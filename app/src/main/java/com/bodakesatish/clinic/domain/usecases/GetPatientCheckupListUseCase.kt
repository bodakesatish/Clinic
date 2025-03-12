package com.bodakesatish.clinic.domain.usecases

import com.bodakesatish.clinic.domain.repository.CheckupRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPatientCheckupListUseCase @Inject constructor(
    private val checkupRepository: CheckupRepository
) {
    operator fun invoke() = checkupRepository.getCheckupList()
}