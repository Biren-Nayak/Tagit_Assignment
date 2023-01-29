package com.example.tagitassignment.caching

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tagitassignment.models.JobOrder
import com.example.tagitassignment.utils.RoomUtils.TABLE_NAME

@Dao
interface JobOrdersDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllJobOrders(): LiveData<List<JobOrder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertJobOrder(vararg jobOrder: JobOrder)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun clearJobOrders()

}