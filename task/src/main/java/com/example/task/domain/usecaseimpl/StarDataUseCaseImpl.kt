package com.example.igaming.com.example.task.domain.usecaseimpl

import com.example.igaming.com.example.task.domain.StarData
import com.example.igaming.com.example.task.domain.usecases.StarDataUseCase
import com.example.igaming.com.example.task.repo.ActionResult
import com.example.igaming.com.example.task.repo.StarDataRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StarDataUseCaseImpl(
    private val starDataRepo: StarDataRepo
) : StarDataUseCase {
    override suspend fun loadStarData(id: Int): ActionResult<StarData> =
        withContext(Dispatchers.IO) {
            starDataRepo.loadStarData(id)
        }
}