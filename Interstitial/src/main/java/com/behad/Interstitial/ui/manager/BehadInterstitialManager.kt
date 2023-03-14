package com.behad.Interstitial.ui.manager

import com.behad.Interstitial.ui.manager.listener.BehadInterstitialListener
import com.behad.Interstitial.ui.model.GetInterstitialFromBackendParams
import com.behad.Interstitial.ui.model.InterstitialData
import com.behad.Interstitial.ui.repository.InterstitialRepository
import kotlinx.coroutines.*

class BehadInterstitialManager(private val repository: InterstitialRepository) {

    private val interstitialJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + interstitialJob)
    private var behadInterstitialListener: BehadInterstitialListener? = null

    fun loadInterstitialAd(deviceId: String, adId: String) {
        coroutineScope.launch {
            handleOnLoading(true)
            repository.getInterstitialFromBackend(
                param = GetInterstitialFromBackendParams(
                    deviceId,
                    adId,
                    "inters",
                ),
            ).onSuccess {
                handleOnAdLoaded(it)
            }.onFailure {
                handleOnError(it)
            }
        }
    }

    fun setListener(behadInterstitialListener: BehadInterstitialListener) {
        this.behadInterstitialListener = behadInterstitialListener
    }

    private suspend fun handleOnLoading(boolean: Boolean) {
        withContext(Dispatchers.Main) {
            behadInterstitialListener?.onLoading(boolean)
        }
    }

    private suspend fun handleOnAdLoaded(data: InterstitialData?) {
        withContext(Dispatchers.Main) {
            behadInterstitialListener?.onAdLoaded(data = data)
        }
    }

    private suspend fun handleOnError(error: Throwable) {
        withContext(Dispatchers.Main) {
            behadInterstitialListener?.onError(error)
        }
    }
}
