package com.bodakesatish.clinic.data.repository

import com.bodakesatish.clinic.data.mapper.CheckupMapper.mapFromDomainModel
import com.bodakesatish.clinic.data.mapper.PatientCheckupMapper.mapToDomainModel
import com.bodakesatish.clinic.data.source.local.dao.CheckupDao
import com.bodakesatish.clinic.data.source.local.entity.CheckUpEntity
import com.bodakesatish.clinic.domain.model.Checkup
import com.bodakesatish.clinic.domain.model.PatientCheckUp
import com.bodakesatish.clinic.domain.repository.CheckupRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CheckupRepositoryImpl @Inject constructor(
    private val checkupDao: CheckupDao
) : CheckupRepository {

    override suspend fun insertOrUpdate(checkup: Checkup): Long {
        return checkupDao.insertOrUpdate(checkup.mapFromDomainModel())
    }

    override suspend fun delete(id: Int) {
        return checkupDao.delete(id)
    }

    override fun getCheckupList(): Flow<List<PatientCheckUp>> {
        return checkupDao.getTodaysCheckupsWithPatientDetails().map { patientCheckUpEntityList ->
            patientCheckUpEntityList.map { patientCheckUpEntity ->
                patientCheckUpEntity.mapToDomainModel()
            }
        }
    }

}