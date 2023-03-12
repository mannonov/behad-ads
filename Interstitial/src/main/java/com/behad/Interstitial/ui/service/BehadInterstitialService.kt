package com.behad.Interstitial.ui.service

import com.behad.Interstitial.ui.model.response.BehadInterstitialResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BehadInterstitialService {

    @GET("/filterAd")
    fun getAd(
        @Query("deviceId") deviceId: String,
        @Query("adId") adId: String,
    ): BehadInterstitialResponse
}
