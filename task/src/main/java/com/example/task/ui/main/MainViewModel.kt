package com.example.igaming.com.example.task.ui.main

import androidx.lifecycle.viewModelScope
import com.example.igaming.com.example.task.base.BaseReducer
import com.example.igaming.com.example.task.base.BaseViewModel
import com.example.igaming.com.example.task.domain.StarData
import com.example.igaming.com.example.task.domain.usecases.StarDataUseCase
import com.example.igaming.com.example.task.mvi.main.MainAction
import com.example.igaming.com.example.task.mvi.main.MainIntent
import com.example.igaming.com.example.task.mvi.main.MainReducer
import com.example.igaming.com.example.task.mvi.main.MainState
import com.example.igaming.com.example.task.repo.ActionResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val starDataUseCase: StarDataUseCase
) : BaseViewModel<MainIntent, MainAction, MainState, BaseReducer<MainState, MainAction>>(
    MainState()
) {

    override val reducer = MainReducer()

    override fun call(action: MainAction) {
        when (action) {
            is MainAction.LoadStarData -> {
                viewModelScope.launch(Dispatchers.Main) {
                    starDataUseCase.loadStarData(action.id).let {
                        when (it) {
                            is ActionResult.SUCCESS<*> -> {
                                dispatchIntent(MainIntent.Init(it.data as? StarData))
                            }
                            is ActionResult.ERROR -> {
                                dispatchIntent(MainIntent.Error(it.message))
                            }
                        }
                    }
                }
            }
            else -> return
        }
    }

    override fun handleIntent(intent: MainIntent): MainAction =
        when (intent) {
            is MainIntent.LoadStarData -> MainAction.LoadStarData(intent.id)
            is MainIntent.Init -> MainAction.Init(data = intent.data)
            is MainIntent.Error -> MainAction.Error(intent.message)
        }
}
