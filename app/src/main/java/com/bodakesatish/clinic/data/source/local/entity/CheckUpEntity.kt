package com.bodakesatish.clinic.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CheckUpEntity.TABLE_NAME)
data class CheckUpEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(Columns.ID)
    val id: Int = 0,
    @ColumnInfo(Columns.PATIENT_ID)
    val patientId: Int = 0,
    @ColumnInfo(Columns.SYMPTOMS)
    val symptoms: String,
    @ColumnInfo(Columns.MEDICINES)
    val medicines: String,
    @ColumnInfo(Columns.OTHER_DETAILS)
    val otherDetails: String,
    @ColumnInfo(Columns.CURRENT_APPOINTMENT_DATE_TIME)
    val currentAppointment: Int,
    @ColumnInfo(Columns.NEXT_APPOINTMENT_DATE_TIME)
    val nextAppointment: Int
) {

    companion object {
        const val TABLE_NAME = "checkup"
    }

    internal object Columns {
        internal const val ID = "id"
        internal const val PATIENT_ID = "patientId"
        internal const val SYMPTOMS = "symptoms"
        internal const val MEDICINES = "medicines"
        internal const val OTHER_DETAILS = "otherDetails"
        internal const val CURRENT_APPOINTMENT_DATE_TIME = "currentAppointmentDateTime"
        internal const val NEXT_APPOINTMENT_DATE_TIME = "nextAppointmentDateTime"
    }

}