package com.example.igaming.com.example.task.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*


abstract class BaseViewModel<INTENT : BaseIntent, ACTION : BaseAction, STATE : BaseState,
        REDUCER : BaseReducer<STATE, ACTION>>(
    initialState: STATE
) : ViewModel(), MviModel<STATE, INTENT> {
    private val currentState = MutableStateFlow(initialState)
    override val state: StateFlow<STATE> get() = currentState.asStateFlow()

    abstract val reducer: REDUCER

    abstract fun handleIntent(intent: INTENT): ACTION

    private fun handleAction(action: ACTION) {
        val oldState = currentState.value
        reducer.reduce(oldState, action).let { newState ->
            if (newState !== oldState) {
                currentState.value = newState
            }
        }
        if (action is BaseCallable) {
            call(action)
        }
    }

    abstract fun call(action: ACTION)

    final override fun dispatchIntent(intent: INTENT) {
        handleAction(action = handleIntent(intent))
    }
}
