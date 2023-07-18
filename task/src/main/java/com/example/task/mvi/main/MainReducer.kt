package com.example.igaming.com.example.task.mvi.main

import com.example.igaming.com.example.task.base.BaseReducer

class MainReducer : BaseReducer<MainState, MainAction>() {
    override fun reduce(state: MainState, action: MainAction): MainState =
        when (action) {
            is MainAction.LoadStarData -> state.copy(showLoading = true)
            is MainAction.Init -> {
                state.copy(showLoading = false, start = true, mainInfo = action.data)
            }
            is MainAction.Error -> {
                state.copy(showLoading = false, start = false, error = true)
            }
        }
}