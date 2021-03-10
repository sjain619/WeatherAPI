package com.eit.weatherapi.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.eit.weatherapi.R
import com.eit.weatherapi.model.WeatherResult

class DetailsFragment: Fragment() {

    private lateinit var temp : TextView
    private lateinit var feelsLike : TextView
    private lateinit var weatherName : TextView
    private lateinit var weatherType : TextView



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            val details = it.get("KEY") as WeatherResult
            displayDetails(details)
        }
        temp = view.findViewById(R.id.tv_tempDigit)
        feelsLike = view.findViewById(R.id.tv_feels)
        weatherName = view.findViewById(R.id.tv_weatherName_detail)
        weatherType = view.findViewById(R.id.tv_weatherType)


    }

    private fun displayDetails(weatherItem: WeatherResult) {

        temp.text = weatherItem.main.temp.toString()
        feelsLike.text = weatherItem.main.feels_like.toString()
        weatherName.text = weatherItem.weather[0].main
        weatherType.text = weatherItem.weather[0].description
    }
}