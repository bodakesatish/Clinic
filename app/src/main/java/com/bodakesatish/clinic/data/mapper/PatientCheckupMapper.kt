package com.bodakesatish.clinic.data.mapper

import com.bodakesatish.clinic.data.source.local.entity.CheckUpEntity
import com.bodakesatish.clinic.data.source.local.entity.PatientCheckUpEntity
import com.bodakesatish.clinic.domain.model.PatientCheckUp

object PatientCheckupMapper : Mapper<PatientCheckUpEntity, PatientCheckUp> {
    override fun PatientCheckUpEntity.mapToDomainModel(): PatientCheckUp {
        return PatientCheckUp(
            checkupId = checkup.id, patientId = checkup.patientId,
            symptoms = checkup.symptoms, medicines = checkup.medicines,
            otherDetails = checkup.otherDetails, currentAppointment = checkup.currentAppointment,
            nextAppointment = checkup.nextAppointment,patientGender = patientGender,
            patientName = patientName, patientAge = patientAge
        )
    }

    override fun PatientCheckUp.mapFromDomainModel(): PatientCheckUpEntity {
        return PatientCheckUpEntity(
            checkup = CheckUpEntity(
                id = checkupId, patientId = patientId,
                symptoms = symptoms, medicines = medicines,
                otherDetails = otherDetails, currentAppointment = currentAppointment,
                nextAppointment = nextAppointment
            ),
            patientName = patientName, patientAge = patientAge, patientGender = patientGender
        )
    }
}