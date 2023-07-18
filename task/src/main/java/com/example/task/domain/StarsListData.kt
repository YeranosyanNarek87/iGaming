package com.example.igaming.com.example.task.domain

data class StarsListData(
    val count: Int = -1,
    val next: String = "",
    val previous: String = "",
    val results: List<StarData> = emptyList()
) : BaseItemData