package com.insearching.cryptotracker.crypto.data.networking

import com.insearching.cryptotracker.core.data.networking.constructUrl
import com.insearching.cryptotracker.core.data.networking.safeCall
import com.insearching.cryptotracker.core.domain.CoinDataSource
import com.insearching.cryptotracker.core.domain.util.NetworkError
import com.insearching.cryptotracker.core.domain.util.Result
import com.insearching.cryptotracker.core.domain.util.map
import com.insearching.cryptotracker.crypto.data.mapper.toCoin
import com.insearching.cryptotracker.crypto.data.mapper.toCoinPrice
import com.insearching.cryptotracker.crypto.data.networking.dto.CoinHistoryDto
import com.insearching.cryptotracker.crypto.data.networking.dto.CoinResponseDto
import com.insearching.cryptotracker.crypto.domain.Coin
import com.insearching.cryptotracker.crypto.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSource(
    private val httpClient: HttpClient
): CoinDataSource {

    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinResponseDto> {
            httpClient.get(
                urlString = com.insearching.cryptotracker.core.data.networking.constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        return safeCall<CoinHistoryDto> {
            httpClient.get(
                urlString = com.insearching.cryptotracker.core.data.networking.constructUrl("/assets/$coinId/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }
}