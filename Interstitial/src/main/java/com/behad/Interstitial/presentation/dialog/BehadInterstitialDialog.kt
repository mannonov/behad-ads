package com.behad.Interstitial.presentation.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.behad.Interstitial.R

class BehadInterstitialDialog(context: Context) : Dialog(context) {
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
    }
}
