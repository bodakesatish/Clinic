package com.bodakesatish.clinic.domain.usecases

import com.bodakesatish.clinic.domain.repository.PatientRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeletePatientUseCase @Inject constructor(
    private val patientRepository: PatientRepository
) {
    suspend operator fun invoke(id: Int) {
        patientRepository.delete(id)
    }
}