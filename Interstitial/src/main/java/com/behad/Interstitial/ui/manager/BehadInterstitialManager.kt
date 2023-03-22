package com.behad.Interstitial.ui.manager

import com.behad.Interstitial.ui.dialog.BehadInterstitialAdImpl
import com.behad.Interstitial.ui.dialog.callback.BehadAdLoadCallback
import com.behad.Interstitial.ui.exception.BackendErrorException
import com.behad.Interstitial.ui.factory.BehadFactory
import com.behad.Interstitial.ui.model.BehadInterInterstitialData
import com.behad.Interstitial.ui.model.GetInterstitialFromBackendParams
import com.behad.Interstitial.ui.repository.InterstitialRepository
import kotlinx.coroutines.*

internal class BehadInterstitialManager {

    private val interstitialJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + interstitialJob)
    private val repository: InterstitialRepository = BehadFactory.getBehadRepository()
    private var behadAdLoadCallback: BehadAdLoadCallback? = null

    fun loadInterstitialAd(
        deviceId: String,
        adId: String,
        behadAdLoadCallback: BehadAdLoadCallback,
    ) {
        this.behadAdLoadCallback = behadAdLoadCallback
        coroutineScope.launch {
            repository.getInterstitialFromBackend(
                param = GetInterstitialFromBackendParams(
                    deviceId,
                    adId,
                    "inters",
                ),
            ).onSuccess {
                if (it != null) {
                    handleOnAdLoaded(it)
                } else {
                    handleOnError(BackendErrorException("Response is null"))
                }
            }.onFailure {
                handleOnError(it)
            }
        }
    }

    private suspend fun handleOnAdLoaded(data: BehadInterInterstitialData) {
        withContext(Dispatchers.Main) {
            behadAdLoadCallback?.onAdLoaded(data = BehadInterstitialAdImpl(data))
        }
    }

    private suspend fun handleOnError(error: Throwable) {
        withContext(Dispatchers.Main) {
            behadAdLoadCallback?.onAdFailedToLoad(error)
        }
    }
}
