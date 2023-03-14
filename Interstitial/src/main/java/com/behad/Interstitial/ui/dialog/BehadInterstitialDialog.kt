package com.behad.Interstitial.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import com.behad.Interstitial.R
import com.behad.Interstitial.ui.model.InterstitialData

class BehadInterstitialDialog(context: Context, private val interData: InterstitialData) : Dialog(context) {
    override fun onStart() {
        super.onStart()
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.window?.setLayout(-1, -1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_behad_interstital_dialog)
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        Log.d("interData", "onCreate: $interData")
    }
}
