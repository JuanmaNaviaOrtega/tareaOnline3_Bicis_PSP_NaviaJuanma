package com.example.tareaonline3bicis

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BiziResponse(
    @SerializedName("totalCount") val totalCount: Int,
    @SerializedName("start") val start: Int,
    @SerializedName("rows") val rows: Int,
    @SerializedName("result") val result: List<BiziStation>
) : Parcelable