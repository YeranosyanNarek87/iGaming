package com.example.igaming.com.example.task.ui.welcome

import androidx.lifecycle.viewModelScope
import com.example.igaming.com.example.task.base.BaseReducer
import com.example.igaming.com.example.task.base.BaseViewModel
import com.example.igaming.com.example.task.domain.StarData
import com.example.igaming.com.example.task.domain.StarsListData
import com.example.igaming.com.example.task.domain.usecases.StarsListUseCase
import com.example.igaming.com.example.task.mvi.welcome.WelcomeAction
import com.example.igaming.com.example.task.mvi.welcome.WelcomeIntent
import com.example.igaming.com.example.task.mvi.welcome.WelcomeReducer
import com.example.igaming.com.example.task.mvi.welcome.WelcomeState
import com.example.igaming.com.example.task.repo.ActionResult
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val starsListUseCase: StarsListUseCase<StarsListData>,
) :
    BaseViewModel<WelcomeIntent, WelcomeAction, WelcomeState, BaseReducer<WelcomeState, WelcomeAction>>(
        WelcomeState()
    ) {

    override val reducer = WelcomeReducer()

    private var data: StarsListData = StarsListData()
    private val indexed = mutableMapOf<String, Int>()

    val onItemClick: (StarData) -> Unit = { starData ->
        indexed[starData.name]?.let { dispatchIntent(WelcomeIntent.OpenStar(it)) }
    }

    init {
        viewModelScope.launch {
            loadData()
        }
    }

    override fun call(action: WelcomeAction) {
        when (action) {
            is WelcomeAction.Retry -> {
                viewModelScope.launch {
                    loadData()
                }
            }
            else -> return
        }
    }

    override fun handleIntent(intent: WelcomeIntent): WelcomeAction =
        when (intent) {
            is WelcomeIntent.Init -> WelcomeAction.Init(data = intent.data)
            is WelcomeIntent.OpenStar -> WelcomeAction.OpenStar(name = intent.id)
            is WelcomeIntent.Error -> WelcomeAction.Error(message = intent.message)
            is WelcomeIntent.Retry -> WelcomeAction.Retry
        }

    fun search(query: String) {
        val items = data.results.filter { it.name.contains(query, ignoreCase = true) }
        dispatchIntent(WelcomeIntent.Init(data.copy(results = items)))
    }

    private suspend fun loadData() {
        starsListUseCase.loadStarsList().let { result ->
            when (result) {
                is ActionResult.SUCCESS<StarsListData> -> {
                    val resultData = result.data
                    resultData.results.forEachIndexed { index, starData ->
                        indexed[starData.name] = index + 1
                    }
                    data = resultData
                    dispatchIntent(WelcomeIntent.Init(data))
                }
                is ActionResult.ERROR -> {
                    dispatchIntent(WelcomeIntent.Error(result.message))
                }
            }
        }
    }
}