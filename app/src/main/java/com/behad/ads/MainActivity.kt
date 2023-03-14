package com.behad.ads

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.behad.Interstitial.ui.dialog.BehadInterstitialDialog
import com.behad.Interstitial.ui.factory.BehadFactory
import com.behad.Interstitial.ui.manager.listener.BehadInterstitialListener
import com.behad.Interstitial.ui.model.InterstitialData

private const val TAG = "InterstitialTag"
class MainActivity : AppCompatActivity() {

    private val behadInterstitialManager = BehadFactory.createBehadInterstitialManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        behadInterstitialManager.loadInterstitialAd(
            "f571812b-9aca-4cf9-8800-1ea5113127bd",
            "BN-67688888",
        )
        behadInterstitialManager.setListener(
            behadInterstitialListener = object : BehadInterstitialListener {
                override fun onLoading(isLoading: Boolean) {
                    Log.d(TAG, "onLoading: $isLoading")
                }

                override fun onAdLoaded(data: InterstitialData?) {
                    val dialog = data?.let { BehadInterstitialDialog(this@MainActivity, it) }
                    dialog?.show()
                }

                override fun onError(error: Throwable) {
                    Log.d(TAG, "onError: $error")
                }
            },
        )
    }
}
