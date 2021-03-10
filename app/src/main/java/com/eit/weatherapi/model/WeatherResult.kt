package com.eit.weatherapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherResult (
    val clouds: Clouds,
    val dt: Float,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
): Parcelable