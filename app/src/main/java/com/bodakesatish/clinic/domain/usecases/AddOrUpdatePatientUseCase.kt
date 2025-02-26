package com.bodakesatish.clinic.domain.usecases

import com.bodakesatish.clinic.domain.model.Patient
import com.bodakesatish.clinic.domain.repository.PatientRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddOrUpdatePatientUseCase @Inject constructor(
    private val patientRepository: PatientRepository
)  {
    suspend operator fun invoke(customer: Patient): Long {
       return patientRepository.insertOrUpdate(customer)
    }
}