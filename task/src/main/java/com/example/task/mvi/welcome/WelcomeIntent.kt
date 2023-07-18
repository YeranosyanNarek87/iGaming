package com.example.igaming.com.example.task.mvi.welcome

import com.example.igaming.com.example.task.base.BaseIntent
import com.example.igaming.com.example.task.domain.StarsListData

sealed class WelcomeIntent : BaseIntent {
    data class Init(val data: StarsListData) : WelcomeIntent()
    object Retry : WelcomeIntent()
    data class Error(val message: String) : WelcomeIntent()
    data class OpenStar(val id: Int) : WelcomeIntent()
}