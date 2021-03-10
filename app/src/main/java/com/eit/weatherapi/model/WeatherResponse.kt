package com.eit.weatherapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherResult>,
    val message: Int
): Parcelable