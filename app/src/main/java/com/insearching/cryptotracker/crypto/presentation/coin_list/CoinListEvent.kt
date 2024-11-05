package com.insearching.cryptotracker.crypto.presentation.coin_list

import com.insearching.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}