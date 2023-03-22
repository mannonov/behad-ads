package com.behad.Interstitial.ui.dialog.callback

import com.behad.Interstitial.ui.dialog.BehadInterstitialAd

interface BehadAdLoadCallback {
    fun onAdFailedToLoad(error: Throwable) {
    }

    fun onAdLoaded(data: BehadInterstitialAd) {
    }
}
