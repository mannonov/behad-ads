package com.behad.Interstitial.ui.factory

import com.behad.Interstitial.ui.constan.BehadInterstitialConstants
import com.behad.Interstitial.ui.service.BehadInterstitialService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BehadServiceFactory {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BehadInterstitialConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()
    }

    fun createBehadInterstitialService() = retrofit.create(BehadInterstitialService::class.java)
}
