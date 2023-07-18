package com.example.igaming.com.example.task.di

import com.example.igaming.com.example.task.api.network.ApiService
import com.example.igaming.com.example.task.domain.StarsListData
import com.example.igaming.com.example.task.domain.usecaseimpl.StarDataUseCaseImpl
import com.example.igaming.com.example.task.domain.usecaseimpl.StarsListUseCaseImpl
import com.example.igaming.com.example.task.domain.usecases.StarDataUseCase
import com.example.igaming.com.example.task.domain.usecases.StarsListUseCase
import com.example.igaming.com.example.task.repo.StarDataRepo
import com.example.igaming.com.example.task.repo.StarDataRepoImpl
import com.example.igaming.com.example.task.repo.StarsListRepo
import com.example.igaming.com.example.task.repo.StarsListRepoImpl
import com.example.igaming.com.example.task.ui.main.MainViewModel
import com.example.igaming.com.example.task.ui.welcome.WelcomeViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    val BASE_URL = "https://swapi.dev/api/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()

    single<ApiService> { retrofit.create(ApiService::class.java) }

    single<StarDataRepo> { StarDataRepoImpl(service = get()) }
    single<StarsListRepo> { StarsListRepoImpl(service = get()) }
    single<StarDataUseCase> { StarDataUseCaseImpl(starDataRepo = get()) }
    single<StarsListUseCase<StarsListData>> { StarsListUseCaseImpl(starsListRepo = get()) }

    viewModel { MainViewModel(starDataUseCase = get()) }
    viewModel { WelcomeViewModel(starsListUseCase = get()) }
}
