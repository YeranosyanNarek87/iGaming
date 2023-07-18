package com.example.igaming.com.example.task.base

import kotlinx.coroutines.flow.Flow

interface BaseAction
interface BaseIntent
interface BaseState
interface BaseCallable

interface MviModel<STATE : BaseState, INTENT : BaseIntent> {
    val state: Flow<STATE>
    fun dispatchIntent(intent: INTENT)
}

