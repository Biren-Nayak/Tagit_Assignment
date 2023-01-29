package com.example.tagitassignment.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tagitassignment.adapters.JobOrderListAdapter
import com.example.tagitassignment.databinding.ActivityMainBinding
import com.example.tagitassignment.models.JobOrder
import com.example.tagitassignment.utils.InputManagerUtils.hideSoftKeyboard
import com.example.tagitassignment.utils.InputManagerUtils.showSoftKeyboard
import com.example.tagitassignment.utils.ModelUtils.JOB_ORDER_DATE_TIME
import com.example.tagitassignment.utils.ModelUtils.JOB_ORDER_ID
import com.example.tagitassignment.utils.ModelUtils.JOB_ORDER_LOCATION
import com.example.tagitassignment.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var imm: InputMethodManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        binding.recyclerView.setupRecyclerView(JobOrderListAdapter())

        binding.searchButton.setOnClickListener { changeSearchVisibility(true) }

        binding.searchCloseButton.setOnClickListener { changeSearchVisibility(false) }



        binding.txtSearch.addTextChangedListener{
            viewModel.setQuery(it.toString())
            Log.e(ContentValues.TAG, viewModel.searchQuery.value.toString())
            Log.e(ContentValues.TAG, viewModel.jobOrdersList.value?.size.toString())
        }
    }


    private fun changeSearchVisibility(isVisible: Boolean) {

        if (isVisible){
            binding.searchButton.visibility = View.GONE
            binding.txtSearch.visibility = View.VISIBLE

            binding.searchCloseButton.visibility = View.VISIBLE

            binding.txtSearch.requestFocus()
            showSoftKeyboard(binding.txtSearch)

        }else{
            binding.searchButton.visibility = View.VISIBLE
            binding.txtSearch.visibility = View.GONE

            binding.searchCloseButton.visibility = View.GONE

            binding.txtSearch.clearFocus()
            binding.txtSearch.text.clear()
            hideSoftKeyboard(binding.txtSearch)

        }

    }



    private fun RecyclerView.setupRecyclerView(adapter: JobOrderListAdapter){
        this.adapter = adapter
        this.layoutManager = LinearLayoutManager(context)

        viewModel.jobOrdersList.observe(this@MainActivity){ jobOrders -> adapter.submitList(
            listOf(JobOrder(JOB_ORDER_DATE_TIME, JOB_ORDER_ID,  JOB_ORDER_LOCATION)) +  jobOrders
        ) }

    }
}