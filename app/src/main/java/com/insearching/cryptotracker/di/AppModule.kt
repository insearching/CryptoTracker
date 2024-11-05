package com.insearching.cryptotracker.di

import com.insearching.cryptotracker.core.data.networking.HttpClientFactory
import com.insearching.cryptotracker.core.domain.CoinDataSource
import com.insearching.cryptotracker.crypto.data.networking.RemoteCoinDataSource
import com.insearching.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { com.insearching.cryptotracker.core.data.networking.HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)
}