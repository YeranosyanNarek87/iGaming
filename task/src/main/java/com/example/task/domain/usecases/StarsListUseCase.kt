package com.example.igaming.com.example.task.domain.usecases

import com.example.igaming.com.example.task.repo.ActionResult

interface StarsListUseCase<T> {
    suspend fun loadStarsList(): ActionResult<T>
}