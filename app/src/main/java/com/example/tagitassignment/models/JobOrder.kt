package com.example.tagitassignment.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tagitassignment.utils.RoomUtils.TABLE_NAME
import com.example.tagitassignment.utils.ModelUtils.JOB_ORDER_DATE_TIME
import com.example.tagitassignment.utils.ModelUtils.JOB_ORDER_DETAIL
import com.example.tagitassignment.utils.ModelUtils.JOB_ORDER_ID
import com.example.tagitassignment.utils.ModelUtils.JOB_ORDER_LOCATION
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Entity(tableName = TABLE_NAME)
@Root(name = JOB_ORDER_DETAIL, strict = false)
data class JobOrder(

    @field:Element(name = JOB_ORDER_DATE_TIME, required = false)
    var dateTime: String = "",

    @PrimaryKey
    @field:Element(name = JOB_ORDER_ID, required = false)
    var orderId: String = "",

    @field:Element(name = JOB_ORDER_LOCATION, required = false)
    var location: String = "",

)