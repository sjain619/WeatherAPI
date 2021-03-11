package com.eit.weatherapi.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.eit.weatherapi.R
import com.eit.weatherapi.util.Constants
import com.eit.weatherapi.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var cityName: EditText
    private lateinit var lookup: Button
    private lateinit var weatherFrame: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cityName = findViewById(R.id.etv_cityName)
        weatherFrame = findViewById(R.id.frame_weather)
        lookup = findViewById(R.id.btn_lookUp)
        lookup.setOnClickListener {
            viewModel.getSearchResults(cityName.text.toString(), Constants.API_KEY)
        }
        viewModel.weatherLiveData.observe(this, {
            Log.d("TAG_X", "Results obtained..")
            cityName.setText(" ")


            val fragment = WeatherListFragment.newInstance(it)
            supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(
                            android.R.anim.slide_in_left,
                            android.R.anim.slide_out_right,
                            android.R.anim.slide_in_left,
                            android.R.anim.slide_out_right
                    )
                    .add(R.id.frame_weather, fragment)
                    .addToBackStack(fragment.tag)
                    .commit()


        })
    }


}
