package com.example.igaming.com.example.task.repo

import com.example.igaming.com.example.task.domain.StarsListData

interface StarsListRepo {
    suspend fun loadStarsList(): ActionResult<StarsListData>
}