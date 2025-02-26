package com.bodakesatish.clinic.data.repository

import com.bodakesatish.clinic.data.mapper.PatientMapper.mapFromDomainModel
import com.bodakesatish.clinic.data.mapper.PatientMapper.mapToDomainModel
import com.bodakesatish.clinic.data.source.local.dao.PatientDao
import com.bodakesatish.clinic.domain.model.Patient
import com.bodakesatish.clinic.domain.repository.PatientRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PatientRepositoryImpl @Inject constructor(
    private val patientDao: PatientDao
) : PatientRepository {

    override suspend fun insertOrUpdate(patient: Patient): Long {
        return patientDao.insertOrUpdate(patient.mapFromDomainModel())
    }

    override suspend fun delete(id: Int) {
        return patientDao.delete(id)
    }

    override fun getPatientList(): Flow<List<Patient>> {
        return patientDao.getPatientList()
            .map { customers ->
                customers.map { customer ->
                    customer.mapToDomainModel()
                }
            }
    }

}