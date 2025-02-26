package com.bodakesatish.clinic.domain.repository

import com.bodakesatish.clinic.domain.model.Patient
import kotlinx.coroutines.flow.Flow

interface PatientRepository {
    suspend fun insertOrUpdate(patient: Patient): Long
    suspend fun delete(id: Int)
    fun getPatientList(): Flow<List<Patient>>
}