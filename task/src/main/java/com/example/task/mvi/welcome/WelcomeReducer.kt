package com.example.igaming.com.example.task.mvi.welcome

import com.example.igaming.com.example.task.base.BaseReducer
import com.example.igaming.com.example.task.domain.NextData

class WelcomeReducer : BaseReducer<WelcomeState, WelcomeAction>() {
    override fun reduce(state: WelcomeState, action: WelcomeAction): WelcomeState =
        when (action) {
            is WelcomeAction.Init -> {
                state.copy(start = true, showLoading = false, welcomeInfo = action.data)
            }
            is WelcomeAction.OpenStar -> {
                state.copy(
                    start = false,
                    openStar = true,
                    showLoading = false,
                    nextData = NextData(action.name)
                )
            }
            is WelcomeAction.Error -> {
                state.copy(
                    start = false,
                    openStar = false,
                    showLoading = false,
                    error = true,
                    errorMessage = action.message
                )
            }
            WelcomeAction.Retry -> state.copy(
                start = false,
                openStar = false,
                error = false,
                showLoading = true
            )
        }
}