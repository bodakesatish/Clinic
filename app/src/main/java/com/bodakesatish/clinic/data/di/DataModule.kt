package com.bodakesatish.clinic.data.di

import com.bodakesatish.clinic.data.repository.CheckupRepositoryImpl
import com.bodakesatish.clinic.data.repository.PatientRepositoryImpl
import com.bodakesatish.clinic.domain.repository.CheckupRepository
import com.bodakesatish.clinic.domain.repository.PatientRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface  DataModule {

    @Binds
    @Singleton
    fun bindPatientRepository(patientRepositoryImpl: PatientRepositoryImpl): PatientRepository

    @Binds
    @Singleton
    fun bindPatientCheckupRepository(checkupRepositoryImpl: CheckupRepositoryImpl): CheckupRepository

}