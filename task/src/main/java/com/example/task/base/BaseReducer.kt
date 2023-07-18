package com.example.igaming.com.example.task.base

abstract class BaseReducer<STATE: BaseState, ACTION: BaseAction> {
    abstract fun reduce(state: STATE, action: ACTION): STATE
}
