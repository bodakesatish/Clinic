package com.bodakesatish.clinic.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreManager {
    // save the app entry
    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>
}