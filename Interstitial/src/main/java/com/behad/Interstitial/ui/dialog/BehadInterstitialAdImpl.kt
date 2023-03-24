package com.behad.Interstitial.ui.dialog

import android.app.Activity
import com.behad.Interstitial.ui.model.BehadInterInterstitialData

internal class BehadInterstitialAdImpl(private val data: BehadInterInterstitialData) :
    BehadInterstitialAd() {

    override fun show(activity: Activity) {
        BehadInterstitialDialog(activity, data).show()
    }
}
