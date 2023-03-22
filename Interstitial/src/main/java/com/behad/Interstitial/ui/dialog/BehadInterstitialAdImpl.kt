package com.behad.Interstitial.ui.dialog

import android.app.Activity
import com.behad.Interstitial.ui.constan.BehadInterstitialConstants
import com.behad.Interstitial.ui.model.BehadInterInterstitialData

internal class BehadInterstitialAdImpl(private val data: BehadInterInterstitialData) :
    BehadInterstitialAd() {

    override fun show(activity: Activity) {
        data.advertisementMediaType = BehadInterstitialConstants.GIF_TYPE
        data.advertisementLink =
            "https://www.easygifanimator.net/images/samples/video-to-gif-sample.gif"
        BehadInterstitialDialog(activity, data).show()
    }
}
