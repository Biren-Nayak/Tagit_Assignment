package com.example.tagitassignment.repository

import com.example.tagitassignment.api.JobOrderApi
import com.example.tagitassignment.caching.JobOrdersDao
import javax.inject.Inject

class JobOrderRepository @Inject constructor(private val api: JobOrderApi, private val dao: JobOrdersDao) {

    fun getOrders() =  dao.getAllJobOrders()

    suspend fun refreshJobOrderLists(){

        val list = api.getJobOrderList().jobOrderList

        if (list.isNotEmpty()){
            dao.clearJobOrders()
            dao.upsertJobOrder(*list.toTypedArray())
        }
    }

}