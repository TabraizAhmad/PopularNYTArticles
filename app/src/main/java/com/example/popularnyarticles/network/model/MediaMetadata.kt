package com.example.popularnyarticles.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MediaMetadata(
    val url: String,
):Parcelable