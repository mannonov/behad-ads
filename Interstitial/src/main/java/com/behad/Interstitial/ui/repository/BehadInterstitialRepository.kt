package com.behad.Interstitial.ui.repository

import com.behad.Interstitial.ui.exception.BackendErrorException
import com.behad.Interstitial.ui.model.BehadInterstitial
import com.behad.Interstitial.ui.service.BehadInterstitialService

class BehadInterstitialRepository(private val service: BehadInterstitialService) {

    suspend fun getAdFromBackend(deviceId: String, adId: String): Result<BehadInterstitial?> {
        return try {
            with(service.getAd(deviceId, adId).await()) {
                if (isSuccessful) {
                    Result.success(value = BehadInterstitial())
                } else {
                    Result.failure(exception = BackendErrorException(errorMessage = errorBody()?.string()))
                }
            }
        } catch (e: Exception) {
            Result.failure(exception = e)
        }
    }
}
