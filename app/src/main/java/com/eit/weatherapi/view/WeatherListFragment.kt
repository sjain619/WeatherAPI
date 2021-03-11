package com.eit.weatherapi.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.eit.weatherapi.R
import com.eit.weatherapi.model.WeatherResponse
import com.eit.weatherapi.model.WeatherResult
import com.eit.weatherapi.view.adapter.WeatherAdapter
import kotlinx.android.synthetic.main.weather_fragment_layout.*

class WeatherListFragment : Fragment(), WeatherAdapter.WeatherDelegate {

    private lateinit var weatherList: RecyclerView
    private lateinit var weatherAdapter: WeatherAdapter
    private lateinit var cityTitle: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.weather_fragment_layout, container, false)
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
            cityTitle = view.findViewById(R.id.tv_cityTitle)
            cityTitle.text = it.city.name
            weatherList.adapter = weatherAdapter

            tv_cityTitle.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }

    companion object {
        const val KEY_WEATHER_DATA = "WeatherListFragment_KEY_DATA"
        fun newInstance(weatherResponse: WeatherResponse): WeatherListFragment {
            return WeatherListFragment().also {
                val bundle = Bundle().also { bundle ->
                    bundle.putParcelable(KEY_WEATHER_DATA, weatherResponse)
                }
                it.arguments = bundle
            }
        }
    }

    override fun showWeatherDetails(weatherResult: WeatherResult) {
        Log.d("TAG_X", "showWeather...")
        val bundle = Bundle()
        val detailsFragment = DetailsFragment()
        val weatherListFragment = WeatherListFragment()
        bundle.putParcelable("KEY", weatherResult)
        detailsFragment.arguments = bundle
        parentFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right,
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right
                )
                .add(R.id.detail_frame, detailsFragment)
                .addToBackStack(weatherListFragment.tag)
                .commit()
    }
}