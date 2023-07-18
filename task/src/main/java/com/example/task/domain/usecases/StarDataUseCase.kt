package com.example.igaming.com.example.task.domain.usecases

import com.example.igaming.com.example.task.domain.StarData
import com.example.igaming.com.example.task.repo.ActionResult

interface StarDataUseCase {
    suspend fun loadStarData(id: Int): ActionResult<StarData>
}