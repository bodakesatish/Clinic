package com.bodakesatish.clinic.domain.usecases

import com.bodakesatish.clinic.domain.repository.PatientRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPatientListUseCase @Inject constructor(
    private val patientRepository: PatientRepository
) {
    operator fun invoke() = patientRepository.getPatientList()
}