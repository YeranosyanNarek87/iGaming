package com.example.igaming.com.example.task.mvi.welcome

import com.example.igaming.com.example.task.base.BaseState
import com.example.igaming.com.example.task.domain.NextData
import com.example.igaming.com.example.task.domain.StarsListData

data class WelcomeState(
    val showLoading: Boolean = true,
    val start: Boolean = false,
    val openStar: Boolean = false,
    val error: Boolean = false,
    val welcomeInfo: StarsListData = StarsListData(),
    val errorMessage: String = "",
    var nextData: NextData = NextData(),
) : BaseState