package com.behad.Interstitial.ui.listener

import com.behad.Interstitial.ui.model.BehadAdConfig

interface BehadInterstitialListener {

    fun onLoaded(config: BehadAdConfig)
    fun onError(throwable: Throwable)
}
