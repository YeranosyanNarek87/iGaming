package com.example.igaming.com.example.task.ui.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.igaming.com.example.task.domain.StarData
import com.example.task.databinding.ItemLayoutBinding

class MyAdapter(
    private val onItemClicked: ((StarData) -> Unit)
) : ListAdapter<StarData, MyViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context)), onItemClicked)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bind(getItem(position))

    private companion object {
        val diffCallback = object :
            DiffUtil.ItemCallback<StarData>() {
            override fun areItemsTheSame(
                oldItem: StarData,
                newItem: StarData
            ): Boolean = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: StarData,
                newItem: StarData
            ): Boolean = oldItem.name == newItem.name
        }
    }
}