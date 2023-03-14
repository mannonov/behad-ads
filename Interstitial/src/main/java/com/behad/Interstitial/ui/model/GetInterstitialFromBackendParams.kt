package com.behad.Interstitial.ui.model

data class GetInterstitialFromBackendParams(
    val deviceId: String,
    val adId: String,
    val type: String,
)
