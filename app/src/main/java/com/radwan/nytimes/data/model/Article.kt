package com.radwan.nytimes.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Article (

    var articleId: Int = 0,
    @SerializedName("id")
    var artId: Long = 0,
    var byline: String = "",
    @SerializedName("abstract")
    var summary: String = "",
    var media: @RawValue List<Media>? = null,
    var published_date: String = "",
    var source: String = "",
    var title: String = "",

): Parcelable


