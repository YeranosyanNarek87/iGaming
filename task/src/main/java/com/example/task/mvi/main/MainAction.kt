package com.example.igaming.com.example.task.mvi.main

import com.example.igaming.com.example.task.base.BaseAction
import com.example.igaming.com.example.task.base.BaseCallable
import com.example.igaming.com.example.task.domain.StarData

sealed class MainAction : BaseAction {
    data class LoadStarData(val id: Int) : MainAction(), BaseCallable
    data class Init(val data: StarData?) : MainAction()
    data class Error(val message: String) : MainAction()
}