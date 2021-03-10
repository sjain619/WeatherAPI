package com.eit.weatherapi.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.eit.weatherapi.R
import com.eit.weatherapi.model.WeatherResult
import com.eit.weatherapi.util.Constants
import com.eit.weatherapi.view.adapter.WeatherAdapter
import com.eit.weatherapi.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity(), WeatherAdapter.WeatherDelegate {

    private val viewModel: WeatherViewModel by viewModels()

    private lateinit var cityName: EditText
    private lateinit var lookup: Button

    private var weatherFragment: WeatherListFragment = WeatherListFragment()
    //weather without Layout

    private lateinit var weather_frame : FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cityName = findViewById(R.id.etv_cityName)
        weather_frame = findViewById(R.id.frame_weather)
        lookup = findViewById(R.id.btn_lookUp)
        lookup.setOnClickListener {
            viewModel.getSearchResults(cityName.text.toString(), Constants.API_KEY)
        }
        viewModel.weatherLiveData.observe(this, androidx.lifecycle.Observer {
            Log.d("TAG_X", "Results obtained...${it[0].dt_txt}")
            weather_frame.visibility = View.VISIBLE
            //var weatherFragment: WeatherListFragment = WeatherListFragment()

            weatherFragment.arguments = Bundle()//adding a Bundle
            val dataSet = Bundle()
            dataSet.putParcelableArrayList("KEY", )
            val f=WeatherListFragment.newInstance(dataSet)


            supportFragmentManager
                .beginTransaction()
                .add(R.id.frame_weather, f)
                .commit()//async

//            weatherAdapter.updateWeatherList(it)
        })
    }

//    override fun showWeatherDetails(weatherResult: WeatherResult) {
//        val bundle =  Bundle()
//
//        bundle.putParcelableArrayList("KEY", weatherResult.)
//        weatherFragment.arguments = bundle
//
//    }
}
