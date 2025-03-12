package com.bodakesatish.clinic.data.di

import android.content.Context
import androidx.room.Room
import com.bodakesatish.clinic.data.source.local.database.PatientDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val  DATABASE_NAME = "patient.db"

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext appContext: Context): PatientDatabase {
        return Room.databaseBuilder(
            appContext,
            PatientDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providesPatientDao(database: PatientDatabase) = database.patientDao()

    @Singleton
    @Provides
    fun providesCheckupDao(database: PatientDatabase) = database.checkupDao()

}