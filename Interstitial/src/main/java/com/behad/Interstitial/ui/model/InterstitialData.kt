package com.behad.Interstitial.ui.model

import com.google.gson.annotations.SerializedName

data class InterstitialData(
    @SerializedName("advertisement_description")
    val advertisementDescription: String? = null,
    @SerializedName("advertisement_link")
    val advertisementLink: String? = null,
    @SerializedName("advertisement_media_type")
    val advertisementMediaType: String? = null,
    @SerializedName("advertisement_title")
    val advertisementTitle: String? = null,
    @SerializedName("campaign_id")
    val campaignId: String? = null,
)
