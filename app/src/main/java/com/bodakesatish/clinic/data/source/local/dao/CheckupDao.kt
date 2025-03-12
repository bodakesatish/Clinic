package com.bodakesatish.clinic.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bodakesatish.clinic.data.source.local.entity.CheckUpEntity
import com.bodakesatish.clinic.data.source.local.entity.PatientCheckUpEntity
import com.bodakesatish.clinic.data.source.local.entity.PatientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(checkUpEntity: CheckUpEntity): Long

    @Update
    suspend fun update(checkUpEntity: CheckUpEntity): Int

    @Query("DELETE FROM ${CheckUpEntity.TABLE_NAME} WHERE ${CheckUpEntity.Columns.ID} = :id")
    fun delete(id: Int)

    @Query("SELECT * FROM ${CheckUpEntity.TABLE_NAME}")
    fun getCheckupList(): Flow<List<CheckUpEntity>>

    @Query("SELECT * FROM ${CheckUpEntity.TABLE_NAME} WHERE ${CheckUpEntity.Columns.ID} = :checkupId")
    suspend fun getCheckUpById(checkupId: Long): CheckUpEntity?

    @Query("SELECT checkups.*, ${PatientEntity.TABLE_NAME}.${PatientEntity.Columns.FIRST_NAME} AS patientName, ${PatientEntity.TABLE_NAME}.${PatientEntity.Columns.AGE} AS patientAge, ${PatientEntity.TABLE_NAME}.${PatientEntity.Columns.GENDER} AS patientGender FROM ${PatientEntity.TABLE_NAME} AS patients, ${CheckUpEntity.TABLE_NAME} AS checkups INNER JOIN ${PatientEntity.TABLE_NAME} ON ${CheckUpEntity.Columns.PATIENT_ID} = patients.id")
    fun getTodaysCheckupsWithPatientDetails(): Flow<List<PatientCheckUpEntity>>

}