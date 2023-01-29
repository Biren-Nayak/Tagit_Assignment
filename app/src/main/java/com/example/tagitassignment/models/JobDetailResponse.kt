package com.example.tagitassignment.models

import com.example.tagitassignment.utils.ModelUtils.JOB_ORDER_ARRAY
import com.example.tagitassignment.utils.ModelUtils.JOB_ORDER_DETAIL
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root(name = JOB_ORDER_ARRAY, strict = false)
data class JobDetailResponse(

    @field:ElementList(name = JOB_ORDER_DETAIL, required = false, inline = true)
    var jobOrderList: List<JobOrder> = mutableListOf()

)

