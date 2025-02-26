package com.bodakesatish.clinic.di

import android.app.Application
import com.bodakesatish.clinic.datastore.DataStoreImpl
import com.bodakesatish.clinic.datastore.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class) // specify the lifecycle. (SingletonComponent::class) means will as long as the application is alive
object AppModule {
    /*
    inside the module we define the dependencies we want to provide
     */

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): DataStoreManager = DataStoreImpl(application)

}