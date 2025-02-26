package com.bodakesatish.clinic.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bodakesatish.clinic.data.source.local.dao.PatientDao
import com.bodakesatish.clinic.data.source.local.entity.PatientEntity

@Database(entities = [PatientEntity::class], version = 1)
abstract class PatientDatabase : RoomDatabase() {
    abstract fun patientDao(): PatientDao
}