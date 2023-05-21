package com.radwan.nytimes.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaMetadata(
    var mediaMetaId: Int = 0,
    var format: String = "",
    var height: Int = 0,
    var url: String = "",
    var width: Int = 0
): Parcelable