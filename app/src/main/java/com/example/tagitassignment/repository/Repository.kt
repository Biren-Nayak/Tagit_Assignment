package com.example.tagitassignment.repository

import androidx.lifecycle.LiveData


interface Repository<T> {
    fun fetchList(): LiveData<List<T>>

    suspend fun refresh()
}