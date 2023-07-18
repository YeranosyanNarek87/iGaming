package com.example.igaming.com.example.task.repo

import com.example.igaming.com.example.task.api.network.ApiService
import com.example.igaming.com.example.task.domain.StarData

class StarDataRepoImpl(
    private val service: ApiService
) : StarDataRepo {

    override suspend fun loadStarData(id: Int): ActionResult<StarData> = try {
        service.getStarInfo(id)?.let {
            if (it.isSuccessful) {
                ActionResult.SUCCESS(starInfoToDataMapper.map(it.body()))
            } else {
                ActionResult.ERROR(it.message())
            }
        } ?: ActionResult.ERROR("Something went wrong")
    } catch (e: Exception) {
        ActionResult.ERROR(e.message ?: "Error")
    }
}
