package com.example.igaming.com.example.task.mvi.main

import com.example.igaming.com.example.task.base.BaseState
import com.example.igaming.com.example.task.domain.StarData

data class MainState(
    val showLoading: Boolean = false,
    val start: Boolean = false,
    val mainInfo: StarData? = null,
    val error: Boolean = false,
) : BaseState
