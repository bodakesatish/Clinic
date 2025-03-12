package com.bodakesatish.clinic.data.mapper

import com.bodakesatish.clinic.data.source.local.entity.CheckUpEntity
import com.bodakesatish.clinic.domain.model.Checkup

object CheckupMapper : Mapper<CheckUpEntity, Checkup> {

    override fun CheckUpEntity.mapToDomainModel(): Checkup {
        return Checkup(
            checkupId = id, patientId = patientId,
            symptoms = symptoms, medicines = medicines,
            otherDetails = otherDetails, currentAppointment = currentAppointment,
            nextAppointment = nextAppointment
        )
    }

    override fun Checkup.mapFromDomainModel(): CheckUpEntity {
        return CheckUpEntity(
            id = checkupId, patientId = patientId,
            symptoms = symptoms, medicines = medicines,
            otherDetails = otherDetails, currentAppointment = currentAppointment,
            nextAppointment = nextAppointment
        )
    }

}