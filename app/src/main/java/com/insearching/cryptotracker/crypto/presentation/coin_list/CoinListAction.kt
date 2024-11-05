package com.insearching.cryptotracker.crypto.presentation.coin_list

import com.insearching.cryptotracker.crypto.presentation.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi) : CoinListAction
}