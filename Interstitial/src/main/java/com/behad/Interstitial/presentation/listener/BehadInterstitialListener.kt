package com.behad.Interstitial.presentation.listener

import com.behad.Interstitial.presentation.model.BehadAdConfig

interface BehadInterstitialListener {

    fun onLoaded(config: BehadAdConfig)
    fun onError(throwable: Throwable)
}
