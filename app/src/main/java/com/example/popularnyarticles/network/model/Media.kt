package com.example.popularnyarticles.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Media(
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadata>,
    val subtype: String,
    val type: String
):Parcelable