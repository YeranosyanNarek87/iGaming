package com.example.igaming.com.example.task.repo

import com.example.igaming.com.example.task.domain.StarData

interface StarDataRepo {
    suspend fun loadStarData(id: Int): ActionResult<StarData>
}