package com.bodakesatish.clinic.domain.model

data class PatientCheckUp(
    val checkupId: Int = 0,
    var patientId: Int = 0,
    var symptoms: String = "",
    var medicines: String = "",
    var otherDetails: String = "",
    var currentAppointment: Int = 0,
    var nextAppointment: Int = 0,
    var patientName: String = "",
    var patientAge: Int = 0,
    var patientGender: String = ""
)