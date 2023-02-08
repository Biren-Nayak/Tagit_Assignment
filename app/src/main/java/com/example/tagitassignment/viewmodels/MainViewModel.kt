package com.example.tagitassignment.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.*
import com.example.tagitassignment.models.JobOrder
import com.example.tagitassignment.repository.JobOrderRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: JobOrderRepositoryImpl) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        Log.e(ContentValues.TAG, throwable.message ?:"")
    }

    private val _searchQuery =  MutableLiveData("")
    val searchQuery : LiveData<String> = _searchQuery

    val jobOrdersList: LiveData<List<JobOrder>> = Transformations.switchMap(_searchQuery) { searchQuery ->
        repository.fetchList().map {
          it.filter {  jobOrder ->
              jobOrder.orderId.contains(searchQuery) ||
              jobOrder.location.contains(searchQuery)
          }
        }
    }

    fun setQuery(text: String) {_searchQuery.value = text}

    private fun refreshJobOrders() = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler){
        repository.refresh()
    }

    init { refreshJobOrders() }

}