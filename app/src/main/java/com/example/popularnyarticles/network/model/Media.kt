package com.example.popularnyarticles.network.model

import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadata>,
    val subtype: String,
    val type: String
)