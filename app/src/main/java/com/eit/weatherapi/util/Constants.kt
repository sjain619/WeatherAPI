package com.eit.weatherapi.util

class Constants {
    //https://api.openweathermap.org/data/2.5/forecast?q={city}&appid={api key}
    companion object{
        const val BASE_URL = "https://api.openweathermap.org"
        const val PATH = "/data/2.5/forecast"
        const val SEARCH_QUERY = "q"
        const val API_KEY = "65d00499677e59496ca2f318eb68c049"
        const val APP_ID = "appid"
    }
}