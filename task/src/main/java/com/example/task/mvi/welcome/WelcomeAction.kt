package com.example.igaming.com.example.task.mvi.welcome

import com.example.igaming.com.example.task.base.BaseAction
import com.example.igaming.com.example.task.base.BaseCallable
import com.example.igaming.com.example.task.domain.StarsListData

sealed class WelcomeAction : BaseAction {
    data class Init(val data: StarsListData) : WelcomeAction()
    object Retry : WelcomeAction(), BaseCallable
    data class OpenStar(val name: Int) : WelcomeAction()
    data class Error(val message: String) : WelcomeAction()
}