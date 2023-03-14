package com.behad.Interstitial.ui.model

import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Deferred
import retrofit2.Response

typealias BehadInterstitialResponse = Deferred<Response<BehadInterstitial>>

data class BehadInterstitial(
    @SerializedName("data")
    val interstitialData: InterstitialData?,
    val message: String?,
    val status: Int?,
)
