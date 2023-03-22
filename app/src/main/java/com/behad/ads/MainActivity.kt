package com.behad.ads

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.behad.Interstitial.ui.dialog.BehadInterstitialAd
import com.behad.Interstitial.ui.dialog.callback.BehadAdLoadCallback

private const val TAG = "InterstitialTag"

class MainActivity : AppCompatActivity(), BehadAdLoadCallback {

    private var behadInterstitialAd: BehadInterstitialAd? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BehadInterstitialAd.load("5b72ef1c-0c05-49f2-8dae-75adfc6d82a0", "BN-67688888", this)
    }

    override fun onAdFailedToLoad(error: Throwable) {
        super.onAdFailedToLoad(error)
        Toast.makeText(this, "error = ${error.message}", Toast.LENGTH_SHORT).show()
    }

    override fun onAdLoaded(data: BehadInterstitialAd) {
        super.onAdLoaded(data)
        behadInterstitialAd = data
        behadInterstitialAd?.show(this)
        Toast.makeText(this, "success = $data", Toast.LENGTH_SHORT).show()
    }
}
