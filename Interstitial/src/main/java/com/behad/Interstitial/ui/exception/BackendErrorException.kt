package com.behad.Interstitial.ui.exception

import com.behad.Interstitial.ui.constan.BehadInterstitialConstants

class BackendErrorException(val errorMessage: String? = BehadInterstitialConstants.BACKEND_ERROR_EXCEPTION_TEXT) :
    Exception(errorMessage)
