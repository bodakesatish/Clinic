package com.bodakesatish.clinic.domain.repository

import com.bodakesatish.clinic.domain.model.Checkup
import com.bodakesatish.clinic.domain.model.PatientCheckUp
import kotlinx.coroutines.flow.Flow

interface CheckupRepository {
    suspend fun insertOrUpdate(checkup: Checkup): Long
    suspend fun delete(id: Int)
    fun getCheckupList(): Flow<List<PatientCheckUp>>
}