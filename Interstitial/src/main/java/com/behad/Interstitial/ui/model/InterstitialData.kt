package com.behad.Interstitial.ui.model

import com.google.gson.annotations.SerializedName

internal typealias BehadInterInterstitialData = InterstitialData

internal data class InterstitialData(
    @SerializedName("advertisement_description")
    val advertisementDescription: String? = null,
    @SerializedName("advertisement_link")
    var advertisementLink: String? = null,
    @SerializedName("advertisement_media_type")
    var advertisementMediaType: String? = null,
    @SerializedName("advertisement_title")
    val advertisementTitle: String? = null,
    @SerializedName("campaign_id")
    val campaignId: String? = null,
    var userId: String? = null,
    var adId: String? = null,
)
