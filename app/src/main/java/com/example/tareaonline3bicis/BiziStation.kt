package com.example.tareaonline3bicis

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BiziStation(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("estado") val estado: String,
    @SerializedName("bicisDisponibles") val bicisDisponibles: Int,
    @SerializedName("anclajesDisponibles") val anclajesDisponibles: Int,
    @SerializedName("geometry") val geometry: Geometry,
    @SerializedName("lastUpdated") val lastUpdated: String,
    @SerializedName("address") val address: String? = null,
    @SerializedName("description") val description: String? = null
) : Parcelable {
    fun isOperative(): Boolean = estado == "IN_SERVICE"
}

@Parcelize
data class Geometry(
    @SerializedName("type") val type: String,
    @SerializedName("coordinates") val coordinates: List<Double>
) : Parcelable