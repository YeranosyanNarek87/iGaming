package com.example.igaming.com.example.task.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.example.igaming.com.example.task.base.BaseFragment
import com.example.igaming.com.example.task.mvi.main.MainAction
import com.example.igaming.com.example.task.mvi.main.MainIntent
import com.example.igaming.com.example.task.mvi.main.MainState
import com.example.task.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment :
    BaseFragment<MainIntent, MainAction, MainState, MainViewModel>() {

    private lateinit var binding: FragmentMainBinding
    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dispatchIntent(MainIntent.LoadStarData(arguments?.getInt("data", -1) ?: -1))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun render(state: MainState) {
        with(state) {
            showHideLoading(showLoading)
            when {
                start -> {
                    binding.run {
                        nameV.text = state.mainInfo?.name
                        heightV.text = state.mainInfo?.height
                        massV.text = state.mainInfo?.mass
                        hairColorV.text = state.mainInfo?.hairColor
                        skinColorV.text = state.mainInfo?.skinColor
                        eyeColorV.text = state.mainInfo?.eyeColor
                        birthYearV.text = state.mainInfo?.birthYear
                        genderV.text = state.mainInfo?.gender
                        homeworldV.text = state.mainInfo?.homeWorld
                    }
                }
                error -> {
                    close()
                }
            }
        }
    }

    private fun close() {
        parentFragmentManager.popBackStack()
    }

    private fun showHideLoading(visibility: Boolean) {
        binding.run {
            loading.isVisible = visibility
        }
    }

    companion object {
        fun newInstance(data: Int?) = MainFragment().apply {
            arguments = bundleOf("data" to data)
        }
    }
}