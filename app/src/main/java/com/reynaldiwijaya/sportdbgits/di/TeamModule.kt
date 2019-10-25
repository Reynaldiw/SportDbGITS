package com.reynaldiwijaya.sportdbgits.di

import com.reynaldiwijaya.sportdbgits.data.footballclub.FootballDataStore
import com.reynaldiwijaya.sportdbgits.data.footballclub.FootballRepository
import com.reynaldiwijaya.sportdbgits.data.footballclub.remote.FootballApi
import com.reynaldiwijaya.sportdbgits.domain.football.FootballInteractor
import com.reynaldiwijaya.sportdbgits.domain.football.FootballUseCase
import com.reynaldiwijaya.sportdbgits.presentation.viewmodel.FootballClubViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val teamModule = module {

    single { FootballApi(get()) }

    single<FootballRepository>{ FootballDataStore(get(), get()) }

    single<FootballUseCase>{ FootballInteractor(get()) }

    viewModel { FootballClubViewModel(get()) }
}