@file:Suppress("DEPRECATION")

package com.example.tagitassignment.di

import android.app.Application
import androidx.room.Room
import com.example.tagitassignment.api.JobOrderApi
import com.example.tagitassignment.repository.JobOrderRepository
import com.example.tagitassignment.caching.JobOrdersDao
import com.example.tagitassignment.caching.JobOrdersDatabase
import com.example.tagitassignment.utils.ApiUtils.BASE_URL
import com.example.tagitassignment.utils.RoomUtils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun myRepoImpl(api: JobOrderApi, dao: JobOrdersDao) = JobOrderRepository(api, dao)

    
    @Provides
    @Singleton
    fun myDaoImpl(database: JobOrdersDatabase): JobOrdersDao = database.jobOrdersDao()

    @Provides
    @Singleton
    fun myDatabaseImpl(application: Application) = Room
        .databaseBuilder(application, JobOrdersDatabase::class.java, DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()


    @Provides
    @Singleton
    fun myApiImpl(): JobOrderApi = Retrofit.Builder()
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(JobOrderApi::class.java)
}