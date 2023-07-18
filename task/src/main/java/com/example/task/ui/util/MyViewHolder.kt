package com.example.igaming.com.example.task.ui.util

import com.example.igaming.com.example.task.domain.StarData
import com.example.task.databinding.ItemLayoutBinding

class MyViewHolder(
    private val itemBinding: ItemLayoutBinding,
    onItemClicked: ((StarData) -> Unit)
) : BaseViewHolder<StarData>(itemBinding.root, onItemClicked) {

    override fun bind(itemData: StarData) {
        itemBinding.run {
            name.text = itemData.name
        }
        super.bind(itemData)
    }
}