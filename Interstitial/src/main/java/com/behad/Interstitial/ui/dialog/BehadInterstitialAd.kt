package com.behad.Interstitial.ui.dialog

import android.app.Activity
import com.behad.Interstitial.ui.dialog.callback.BehadAdLoadCallback
import com.behad.Interstitial.ui.manager.BehadInterstitialManager

abstract class BehadInterstitialAd {

    companion object {
        fun load(deviceId: String?, adId: String?, loadCallback: BehadAdLoadCallback?) {
            checkNotNull(deviceId) { "Device ID cannot be null." }
            checkNotNull(adId) { "Ad ID cannot be null." }
            checkNotNull(loadCallback) { "BehadAdLoadCallback cannot be null." }
            BehadInterstitialManager().loadInterstitialAd(deviceId, adId, loadCallback)
        }
    }

    abstract fun show(activity: Activity)
}
