package com.example.tagitassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tagitassignment.adapters.JobOrderListAdapter.JobOrderListViewHolder
import com.example.tagitassignment.databinding.ItemJobOrderBinding
import com.example.tagitassignment.models.JobOrder



class JobOrderListAdapter :ListAdapter<JobOrder, JobOrderListViewHolder>(DiffCallBack) {

    class JobOrderListViewHolder(private val binding: ItemJobOrderBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(jobOrderDtl: JobOrder){
            binding.jobOrder = jobOrderDtl
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        JobOrderListViewHolder(ItemJobOrderBinding.inflate(
            LayoutInflater.from(parent.context),parent, false
        )
    )

    override fun onBindViewHolder(holder: JobOrderListViewHolder, position: Int) = holder.bind(
        getItem(position)
    )


    companion object DiffCallBack: DiffUtil.ItemCallback<JobOrder>(){
        override fun areItemsTheSame(oldItem: JobOrder, newItem: JobOrder) =  oldItem.orderId == newItem.orderId
        override fun areContentsTheSame(oldItem: JobOrder, newItem: JobOrder) = oldItem == newItem
    }

}