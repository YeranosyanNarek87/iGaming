package com.example.igaming.com.example.task.mvi.main

import com.example.igaming.com.example.task.base.BaseIntent
import com.example.igaming.com.example.task.domain.StarData

sealed class MainIntent : BaseIntent {
    data class LoadStarData(val id: Int) : MainIntent()
    data class Init(val data: StarData?) : MainIntent()
    data class Error(val message: String) : MainIntent()
}