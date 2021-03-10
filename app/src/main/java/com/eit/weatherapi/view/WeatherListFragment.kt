package com.eit.weatherapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.eit.weatherapi.R
import com.eit.weatherapi.model.WeatherResponse
import com.eit.weatherapi.model.WeatherResult
import com.eit.weatherapi.view.adapter.WeatherAdapter
import com.eit.weatherapi.viewmodel.WeatherViewModel
import okhttp3.internal.notify

class WeatherListFragment: Fragment(), WeatherAdapter.WeatherDelegate {

    private lateinit var weatherList: RecyclerView
    private val viewModel: WeatherViewModel by viewModels()
    private var detailsFragment = DetailsFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
     * View layout properly inflated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        publicRepositoryItemAdapter = RepositoryItemAdapter(listOf(), this, gitViewModel, userToken)

        arguments.getParcelable<WeatherResponse>(KEY_WEATHER_DATA)
        weatherList = view.findViewById(R.id.rv_weather)
        weatherList.adapter = weatherAdapter
        weatherAdapter.updateWeatherList() //Pass Array list
        weatherAdapter.notifyDataSetChanged()
    }

    private val weatherAdapter = WeatherAdapter(mutableListOf(), this)
    override fun showWeatherDetails(weatherResult: WeatherResult) {

        detailsFragment.apply {

            val bundle = Bundle().also {
                it.putParcelable("KEY", weatherResult)
            }


            childFragmentManager
                .beginTransaction()
                .add(R.id.detail_frame, detailsFragment)
                .commit()
        }


    }

    companion object{
        const val KEY_WEATHER_DATA = "WeatherListFragment_KEY_DATA"
        fun newInstance(param: WeatherResponse): WeatherListFragment{
            return WeatherListFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_WEATHER_DATA, param)
                }
            }
        }
    }
}