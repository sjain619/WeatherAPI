package com.eit.weatherapi.network

import com.eit.weatherapi.model.WeatherResponse
import com.eit.weatherapi.util.Constants.Companion.BASE_URL
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class WeatherRetrofit {

    private var weatherAPI: WeatherAPI
    init {
        weatherAPI = createWeatherAPI(createRetrofitInstance())
    }

    private fun createWeatherAPI(retrofit: Retrofit): WeatherAPI =
        retrofit.create(WeatherAPI::class.java)

    private fun createRetrofitInstance(): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getSearchQuery(searchQuery: String, key: String): Observable<WeatherResponse> =
         weatherAPI.searchCity(searchQuery, key)

}