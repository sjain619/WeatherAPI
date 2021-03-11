package com.eit.weatherapi.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eit.weatherapi.R
import com.eit.weatherapi.model.WeatherResult

class WeatherAdapter(private var weatherList: List<WeatherResult>,
                     private val weatherDelegate: WeatherDelegate) :
        RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    interface WeatherDelegate {
        fun showWeatherDetails(weatherResult: WeatherResult)
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val weather: TextView = itemView.findViewById(R.id.tv_weatherName)
        private val tempDigit: TextView = itemView.findViewById(R.id.tv_Temp)
        private val weatherIcon: ImageView = itemView.findViewById(R.id.iv_weatherIcon)

        fun onBind(dataItem: WeatherResult, delegate: WeatherDelegate) {
            weather.text = dataItem.weather[0].main
            tempDigit.text = dataItem.main.temp.toString()
            itemView.setOnClickListener {
                delegate.showWeatherDetails(dataItem)
            }

            when (dataItem.weather[0].main) {
                "Clouds" -> weatherIcon.setImageResource(R.drawable.ic_cloudy)
                "Clear" -> weatherIcon.setImageResource(R.drawable.ic_sun)
                "Rain" -> weatherIcon.setImageResource(R.drawable.ic_rain)
                "Snow" -> weatherIcon.setImageResource(R.drawable.ic_snowy)
                else -> weatherIcon.setImageResource(R.drawable.noimg)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_item_layout, parent, false)
        return WeatherViewHolder(itemView)
    }

    override fun getItemCount(): Int = weatherList.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.onBind(weatherList[position], weatherDelegate)
    }
}