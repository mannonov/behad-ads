package com.behad.Interstitial.ui.model.response

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Response

typealias BehadInterstitialResponse = Deferred<Response<ResponseBody?>>

class BehadInterstitialNetwork
