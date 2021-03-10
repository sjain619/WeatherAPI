package com.eit.weatherapi.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eit.weatherapi.R
import com.eit.weatherapi.model.WeatherResponse
import com.eit.weatherapi.model.WeatherResult
import com.eit.weatherapi.view.adapter.WeatherAdapter
import com.eit.weatherapi.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.weather_fragment_layout.*
import okhttp3.internal.notify

class WeatherListFragment: Fragment(), WeatherAdapter.WeatherDelegate {

    private lateinit var weatherList: RecyclerView
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.weather_fragment_layout, container, false)
        return view
    }

    /**
     * View layout properly inflated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data =
             arguments?.getParcelable<WeatherResponse>(KEY_WEATHER_DATA)
        data?.let {
            weatherAdapter = WeatherAdapter(it.list, this)
            weatherList = view.findViewById(R.id.rv_weather)
            weatherList.adapter = weatherAdapter
        }
    }
//
    companion object{
        const val KEY_WEATHER_DATA = "WeatherListFragment_KEY_DATA"
        fun newInstance(weatherResponse: WeatherResponse): WeatherListFragment{
            return WeatherListFragment().also {
                val bundle = Bundle().also { bndle->
                    bndle.putParcelable(KEY_WEATHER_DATA, weatherResponse)
                }
                it.arguments = bundle
            }
        }
    }

    override fun showWeatherDetails(weatherResult: WeatherResult) {
        Log.d("TAG_X", "showWeather...")
        val bundle =  Bundle()
        val detailsFragment = DetailsFragment()
        bundle.putParcelable("KEY", weatherResult)
        detailsFragment.arguments = bundle

        detail_frame.visibility = View.VISIBLE

        childFragmentManager
            .beginTransaction()
            .add(R.id.detail_frame, detailsFragment)
            .addToBackStack(detailsFragment.tag)
            .commit()
    }
}