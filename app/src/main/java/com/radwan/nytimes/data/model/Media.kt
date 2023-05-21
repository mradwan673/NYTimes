package com.radwan.nytimes.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Media(
    var mediaId: Int = 0,
    var approved_for_syndication: Int = 0,
    var caption: String = "",
    var copyright: String = "",
    @SerializedName("media-metadata")
    var mediaMetadata:  @RawValue List<MediaMetadata>? = null,
    var subtype: String = "",
    var type: String = ""
): Parcelable