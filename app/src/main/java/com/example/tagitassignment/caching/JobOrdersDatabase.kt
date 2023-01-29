package com.example.tagitassignment.caching

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tagitassignment.models.JobOrder

@Database(entities = [JobOrder::class], version = 1, exportSchema = false)
abstract class JobOrdersDatabase: RoomDatabase(){
    abstract fun jobOrdersDao(): JobOrdersDao
}