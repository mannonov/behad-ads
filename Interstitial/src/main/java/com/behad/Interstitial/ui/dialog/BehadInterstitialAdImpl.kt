package com.behad.Interstitial.ui.dialog

import android.app.Activity
import com.behad.Interstitial.ui.constan.BehadInterstitialConstants
import com.behad.Interstitial.ui.model.BehadInterInterstitialData

internal class BehadInterstitialAdImpl(private val data: BehadInterInterstitialData) :
    BehadInterstitialAd() {

    override fun show(activity: Activity) {
        data.advertisementMediaType = BehadInterstitialConstants.IMAGE_TYPE
        data.advertisementLink =
            "https://www.simplilearn.com/ice9/free_resources_article_thumb/How_to_Become_an_Android_Developer.jpg"
        BehadInterstitialDialog(activity, data).show()
    }
}
