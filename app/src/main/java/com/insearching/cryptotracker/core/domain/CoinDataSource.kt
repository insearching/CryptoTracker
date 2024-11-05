package com.insearching.cryptotracker.core.domain

import com.insearching.cryptotracker.core.domain.util.NetworkError
import com.insearching.cryptotracker.core.domain.util.Result
import com.insearching.cryptotracker.crypto.domain.Coin
import com.insearching.cryptotracker.crypto.domain.CoinPrice
import java.time.ZonedDateTime


interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
    suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError>
}