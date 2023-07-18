package com.example.igaming.com.example.task.repo

import com.example.igaming.com.example.task.api.network.ApiService
import com.example.igaming.com.example.task.domain.StarsListData

class StarsListRepoImpl(
    private val service: ApiService
) : StarsListRepo {
    override suspend fun loadStarsList(): ActionResult<StarsListData> = try {
        service.getStarsList()?.run {
            if (isSuccessful) {
                ActionResult.SUCCESS(starListInfoToDataMapper.map(body()))
            } else {
                ActionResult.ERROR(message())
            }
        } ?: ActionResult.ERROR("Something went wrong")
    } catch (e: Exception) {
        ActionResult.ERROR(e.message ?: "Error")
    }
}