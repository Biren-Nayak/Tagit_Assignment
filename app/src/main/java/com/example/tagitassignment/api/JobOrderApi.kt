package com.example.tagitassignment.api

import com.example.tagitassignment.models.JobDetailResponse
import com.example.tagitassignment.utils.ApiUtils.DATE_TIME
import com.example.tagitassignment.utils.ApiUtils.DATE_TIME_VALUE
import com.example.tagitassignment.utils.ApiUtils.JOB_ORDER
import com.example.tagitassignment.utils.ApiUtils.USER_NAME
import com.example.tagitassignment.utils.ApiUtils.USER_NAME_VALUE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface JobOrderApi {

    @Headers("Content-type: text/xml")
    @GET(JOB_ORDER)
    suspend fun getJobOrderList(
        @Query(USER_NAME) username: String = USER_NAME_VALUE,
        @Query(DATE_TIME) dateTime: String = DATE_TIME_VALUE,
    ): JobDetailResponse


}