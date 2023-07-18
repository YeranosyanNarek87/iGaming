package com.example.igaming.com.example.task.api.network

import com.example.igaming.com.example.task.repo.StarsListResponse
import com.example.igaming.com.example.task.repo.StarsResponses
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("people/{id}")
    suspend fun getStarInfo(@Path("id") personId: Int): Response<StarsResponses>?

    @GET("people/")
    suspend fun getStarsList(): Response<StarsListResponse>?
}