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
        BehadInterstitialAd.load("f571812b-9aca-4cf9-8800-1ea5113127bd", "BN-67688888", this)
    }

    override fun onAdFailedToLoad(error: Throwable) {
        super.onAdFailedToLoad(error)
        Toast.makeText(this, "error = ${error.message}", Toast.LENGTH_SHORT).show()
    }

    override fun onAdLoaded(data: BehadInterstitialAd) {
        super.onAdLoaded(data)
        behadInterstitialAd = data
        Toast.makeText(this, "success = $data", Toast.LENGTH_SHORT).show()
    }
}
