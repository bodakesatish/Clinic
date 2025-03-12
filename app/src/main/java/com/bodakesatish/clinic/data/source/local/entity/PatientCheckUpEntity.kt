package com.bodakesatish.clinic.data.source.local.entity

import androidx.room.Embedded

data class PatientCheckUpEntity (
    @Embedded val checkup: CheckUpEntity,
    val patientName: String,
    val patientAge: Int,
    val patientGender: String
)