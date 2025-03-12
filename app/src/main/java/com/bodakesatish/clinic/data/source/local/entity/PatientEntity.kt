package com.bodakesatish.clinic.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = PatientEntity.TABLE_NAME)
data class PatientEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(Columns.ID)
    val id: Int = 0,
    @ColumnInfo(Columns.FIRST_NAME)
    val firstName: String,
    @ColumnInfo(Columns.LAST_NAME)
    val lastName: String,
    @ColumnInfo(Columns.PHONE)
    val phone: String,
    @ColumnInfo(Columns.GENDER)
    val gender: String,
    @ColumnInfo(Columns.AGE)
    val age: Int
) {

    companion object {
        const val TABLE_NAME = "patient"
    }

    internal object Columns {
        internal const val ID = "id"
        internal const val FIRST_NAME = "first_name"
        internal const val LAST_NAME = "last_name"
        internal const val PHONE = "phone"
        internal const val GENDER = "gender"
        internal const val AGE = "age"
    }

}