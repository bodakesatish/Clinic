package com.bodakesatish.clinic.data.mapper

import com.bodakesatish.clinic.data.source.local.entity.PatientEntity
import com.bodakesatish.clinic.domain.model.Patient

object PatientMapper : Mapper<PatientEntity, Patient> {
    override fun PatientEntity.mapToDomainModel(): Patient {
        return Patient(id = id, firstName = firstName, lastName = lastName, phone = phone)
    }

    override fun Patient.mapFromDomainModel(): PatientEntity {
        return PatientEntity(id = id, firstName = firstName, lastName = lastName, phone = phone)
    }
}