package com.example.tagitassignment.repository

import com.example.tagitassignment.api.JobOrderApi
import com.example.tagitassignment.caching.JobOrdersDao
import com.example.tagitassignment.models.JobOrder
import javax.inject.Inject

class JobOrderRepositoryImpl @Inject constructor(private val api: JobOrderApi, private val dao: JobOrdersDao): Repository<JobOrder>{

    override fun fetchList() =  dao.getAllJobOrders()

    override suspend fun refresh(){

        val list = api.getJobOrderList().jobOrderList

        if (list.isNotEmpty()){
            dao.clearJobOrders()
            dao.upsertJobOrder(*list.toTypedArray())
        }
    }

}