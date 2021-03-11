package com.eit.weatherapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.eit.weatherapi.R
import com.eit.weatherapi.model.WeatherResult
import kotlinx.android.synthetic.main.details_fragment_layout.*

class DetailsFragment: Fragment() {

    private lateinit var temp : TextView
    private lateinit var feelsLike : TextView
    private lateinit var weatherName : TextView
    private lateinit var weatherType : TextView
    private lateinit var detailLayout: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        temp = view.findViewById(R.id.tv_tempDigit)
        feelsLike = view.findViewById(R.id.tv_feels)
        weatherName = view.findViewById(R.id.tv_weatherName_detail)
        weatherType = view.findViewById(R.id.tv_weatherType)
        detailLayout = view.findViewById(R.id.detail_layout)

        close_fragment.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        arguments?.let {

            val details = it.get("KEY") as WeatherResult
            displayDetails(details)
        }



    }

    private fun displayDetails(weatherItem: WeatherResult) {

        temp.text = weatherItem.main.temp.toString()
        feelsLike.text = weatherItem.main.feels_like.toString()
        weatherName.text = weatherItem.weather[0].main
        weatherType.text = weatherItem.weather[0].description

        when (weatherItem.weather[0].main){
            "Clouds" -> detailLayout.setBackgroundResource(R.drawable.cloudy)
            "Clear" -> detailLayout.setBackgroundResource(R.drawable.sunny)
            "Rain" -> detailLayout.setBackgroundResource(R.drawable.rainy)
            "Snow" -> detailLayout.setBackgroundResource(R.drawable.snowy)
            else -> detailLayout.setBackgroundResource(R.drawable.night)
        }
    }
}