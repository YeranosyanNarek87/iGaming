package com.example.igaming.com.example.task.domain.usecaseimpl

import com.example.igaming.com.example.task.domain.StarsListData
import com.example.igaming.com.example.task.domain.usecases.StarsListUseCase
import com.example.igaming.com.example.task.repo.ActionResult
import com.example.igaming.com.example.task.repo.StarsListRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StarsListUseCaseImpl(
    private val starsListRepo: StarsListRepo
) : StarsListUseCase<StarsListData> {
    override suspend fun loadStarsList(): ActionResult<StarsListData> =
        withContext(Dispatchers.IO) {
            starsListRepo.loadStarsList()
        }
}