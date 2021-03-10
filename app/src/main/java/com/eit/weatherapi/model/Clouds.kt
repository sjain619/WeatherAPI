package com.eit.weatherapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Clouds(
    val all: Int
):Parcelable