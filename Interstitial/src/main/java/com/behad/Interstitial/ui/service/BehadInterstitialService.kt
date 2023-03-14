package com.behad.Interstitial.ui.service

import com.behad.Interstitial.ui.model.BehadInterstitialResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BehadInterstitialService {

    @GET("/filterAd")
    fun getAdd(
        @Query("deviceId") deviceId: String,
        @Query("adId") adId: String,
        @Query("type") type: String,
    ): BehadInterstitialResponse
}
