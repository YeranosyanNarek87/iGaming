package com.example.igaming.com.example.task.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseFragment<INTENT : BaseIntent, ACTION : BaseAction, STATE : BaseState,
        VIEWMODEL : BaseViewModel<INTENT, ACTION, STATE, BaseReducer<STATE, ACTION>>> :
    Fragment() {

    protected abstract val viewModel: VIEWMODEL

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                render(it)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    protected fun dispatchIntent(intent: INTENT) {
        viewModel.dispatchIntent(intent)
    }

    abstract fun render(state: STATE)


}