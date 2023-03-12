package com.behad.ads

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.behad.Interstitial.presentation.dialog.BehadInterstitialDialog
import com.behad.Interstitial.presentation.model.BehadAdConfig

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dialog = BehadInterstitialDialog(context = this, behadAdConfig = BehadAdConfig())
        dialog.show()
    }
}
