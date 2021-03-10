package com.eit.weatherapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eit.weatherapi.model.WeatherResponse
import com.eit.weatherapi.model.WeatherResult
import com.eit.weatherapi.network.WeatherRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel: ViewModel() {
    private val compositeDisposable: CompositeDisposable =  CompositeDisposable()

    val weatherLiveData: MutableLiveData<WeatherResponse> = MutableLiveData()

    private val weatherRetrofit: WeatherRetrofit = WeatherRetrofit()

    fun getSearchResults(searchQuery: String, key: String) {
        //RxJava implementation
        compositeDisposable.add(
            weatherRetrofit.getSearchQuery(searchQuery,key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    Log.d("TAG_X", "$it")


                    if(it.list.isNotEmpty())
                        weatherLiveData.postValue(it)
                    compositeDisposable.clear()
                }, {

                    Log.d("TAG_X", "this -? ${it.localizedMessage}")
                })
        )
    }

}