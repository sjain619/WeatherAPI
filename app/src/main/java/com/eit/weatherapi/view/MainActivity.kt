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

class MainActivity : AppCompatActivity(){

    private val viewModel: WeatherViewModel by viewModels()

    private lateinit var cityName: EditText
    private lateinit var lookup: Button
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
            Log.d("TAG_X", "Results obtained..")
            cityName.setText(" ")
            //progress bar
//            weather_frame.visibility = View.VISIBLE

            val fragment = WeatherListFragment.newInstance(it)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.frame_weather, fragment)
                .addToBackStack(fragment.tag)
                .commit()//async


        })
    }


}
