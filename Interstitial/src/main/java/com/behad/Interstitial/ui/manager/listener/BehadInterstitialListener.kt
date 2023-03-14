package com.behad.Interstitial.ui.manager.listener

import com.behad.Interstitial.ui.model.InterstitialData

interface BehadInterstitialListener {

    fun onLoading(isLoading: Boolean)

    fun onAdLoaded(data: InterstitialData?)

    fun onError(error: Throwable)
}
