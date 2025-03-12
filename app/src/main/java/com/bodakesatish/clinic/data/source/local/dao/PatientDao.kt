package com.bodakesatish.clinic.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bodakesatish.clinic.data.source.local.entity.PatientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(patientEntity: PatientEntity): Long

    @Update
    suspend fun update(patientEntity: PatientEntity): Int

    @Query("DELETE FROM ${PatientEntity.TABLE_NAME} WHERE ${PatientEntity.Columns.ID} = :id")
    fun delete(id: Int)

    @Query("SELECT * FROM ${PatientEntity.TABLE_NAME}")
    fun getPatientList(): Flow<List<PatientEntity>>

    @Query("SELECT * FROM ${PatientEntity.TABLE_NAME} WHERE ${PatientEntity.Columns.ID} = :patientId")
    suspend fun getPatientById(patientId: Long): PatientEntity?

}