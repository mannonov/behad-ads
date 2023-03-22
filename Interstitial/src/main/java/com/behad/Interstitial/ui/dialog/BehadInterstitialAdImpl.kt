package com.behad.Interstitial.ui.dialog

import android.app.Activity
import com.behad.Interstitial.ui.model.BehadInterInterstitialData

internal class BehadInterstitialAdImpl(private val data: BehadInterInterstitialData) :
    BehadInterstitialAd() {

    override fun show(activity: Activity) {
        data.advertisementMediaType = "video"
        data.advertisementLink = "https://user-images.githubusercontent.com/74708507/226952606-40fb7581-3271-4a24-8db4-44d009ff89f7.mp4"
        BehadInterstitialDialog(activity, data).show()
    }
}
