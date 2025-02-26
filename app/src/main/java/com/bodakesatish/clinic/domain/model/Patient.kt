package com.bodakesatish.clinic.domain.model

import java.io.Serializable

data class Patient(
    val id: Int = 0,
    var firstName: String = "",
    var lastName: String = "",
    var phone: String = ""
)
