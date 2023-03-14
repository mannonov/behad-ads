package com.behad.Interstitial.ui.repository

import com.behad.Interstitial.ui.exception.BackendErrorException
import com.behad.Interstitial.ui.model.GetInterstitialFromBackendParams
import com.behad.Interstitial.ui.model.InterstitialData
import com.behad.Interstitial.ui.service.BehadInterstitialService

typealias GetInterstitialFromBackendParam = GetInterstitialFromBackendParams
typealias GetInterstitialFromBackendResult = Result<InterstitialData?>

class InterstitialRepository(private val service: BehadInterstitialService) {

    suspend fun getInterstitialFromBackend(param: GetInterstitialFromBackendParam): GetInterstitialFromBackendResult {
        return try {
            with(
                service.getAdd(deviceId = param.deviceId, adId = param.adId, type = param.type)
                    .await(),
            ) {
                if (isSuccessful) {
                    Result.success(body()?.interstitialData)
                } else {
                    Result.failure(
                        BackendErrorException(
                            errorMessage = errorBody()?.string().toString(),
                        ),
                    )
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
