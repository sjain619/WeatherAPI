package com.eit.weatherapi.network

import com.eit.weatherapi.model.WeatherResponse
import com.eit.weatherapi.util.Constants.Companion.API_KEY
import com.eit.weatherapi.util.Constants.Companion.APP_ID
import com.eit.weatherapi.util.Constants.Companion.PATH
import com.eit.weatherapi.util.Constants.Companion.SEARCH_QUERY
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET(PATH)
    fun searchCity(
        @Query(SEARCH_QUERY) searchQuery: String,
        @Query(APP_ID) key: String): Observable<WeatherResponse>
}