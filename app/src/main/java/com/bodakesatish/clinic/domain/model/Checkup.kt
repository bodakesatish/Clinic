package com.bodakesatish.clinic.domain.model

data class Checkup(
    val checkupId: Int = 0,
    var patientId: Int = 0,
    var symptoms: String = "",
    var medicines: String = "",
    var otherDetails: String = "",
    var currentAppointment: Int = 0,
    var nextAppointment: Int = 0,
)