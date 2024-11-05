package com.insearching.cryptotracker.core.data.networking

import com.insearching.cryptotracker.BuildConfig

fun constructUrl(url: String): String {
    return when {
        url.contains(com.insearching.cryptotracker.BuildConfig.BASE_URL) -> url
        url.startsWith("/") -> com.insearching.cryptotracker.BuildConfig.BASE_URL + url.drop(1)
        else -> com.insearching.cryptotracker.BuildConfig.BASE_URL + url
    }
}