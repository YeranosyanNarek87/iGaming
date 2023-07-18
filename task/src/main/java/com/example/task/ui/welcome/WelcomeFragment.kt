package com.example.igaming.com.example.task.ui.welcome

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.igaming.com.example.task.base.BaseFragment
import com.example.igaming.com.example.task.domain.StarData
import com.example.igaming.com.example.task.mvi.welcome.WelcomeAction
import com.example.igaming.com.example.task.mvi.welcome.WelcomeIntent
import com.example.igaming.com.example.task.mvi.welcome.WelcomeState
import com.example.igaming.com.example.task.ui.main.MainFragment
import com.example.igaming.com.example.task.ui.util.MyAdapter
import com.example.task.R
import com.example.task.databinding.FragmentWelcomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WelcomeFragment :
    BaseFragment<WelcomeIntent, WelcomeAction, WelcomeState, WelcomeViewModel>() {

    override val viewModel: WelcomeViewModel by viewModel()
    private lateinit var binding: FragmentWelcomeBinding
    private val adapter: MyAdapter by lazy { MyAdapter(viewModel.onItemClick) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemList.adapter = adapter

        binding.run {
            retry.setOnClickListener {
                dispatchIntent(WelcomeIntent.Retry)
            }

            starName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) = Unit

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
                    Unit

                override fun afterTextChanged(s: Editable?) {
                    val length = s?.length ?: 0
                    viewModel.search(if (length >= 2) s?.toString().orEmpty() else "")
                }
            })
        }
    }

    override fun render(state: WelcomeState) {
        with(state) {
            showHideLoading(showLoading)
            when {
                start -> {
                    renderList(welcomeInfo.results)
                }
                openStar -> {
                    openNextScreen(nextData.name)
                }
                error -> {
                    Toast.makeText(
                        context,
                        errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {}
            }
        }
    }

    private fun renderList(data: List<StarData>) {
        adapter.submitList(data)
    }


    private fun showHideLoading(visibility: Boolean) {
        binding.loading.isVisible = visibility
    }

    private fun openNextScreen(id: Int) {
        (activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            view?.windowToken,
            0
        )
        parentFragmentManager.beginTransaction().add(
            R.id.container, MainFragment.newInstance(id)
        ).addToBackStack(null).commit()
    }

    companion object {
        fun newInstance() = WelcomeFragment()
    }
}