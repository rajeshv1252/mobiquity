package com.mobiquity.code.challenge.ui.city

import androidx.lifecycle.MutableLiveData
import com.mobiquity.code.challenge.data.model.CurrentWeather
import com.mobiquity.code.challenge.data.remote.response.fivedayweather.FiveDayResponse
import com.mobiquity.code.challenge.data.repository.UserRepository
import com.mobiquity.code.challenge.ui.base.BaseViewModel
import com.mobiquity.code.challenge.utils.network.NetworkHelper
import com.mobiquity.code.challenge.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class CityViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    companion object {
        val TAG = "CityViewModel"
    }

    val fetchData: MutableLiveData<Boolean> = MutableLiveData()
    val currentWeatherData = MutableLiveData<CurrentWeather>()
    val fivedaysWeatherData = MutableLiveData<FiveDayResponse>()

    override fun onCreate() {

    }

    fun fetchCurrentWeather(lat: String, long: String, units: String) {
        fetchData.postValue(true)
        compositeDisposable.addAll(
            userRepository.currentWeather(lat, long, units)
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    fetchData.postValue(false)
                    currentWeatherData.postValue(it)
                }, {
                    fetchData.postValue(false)
                    handleNetworkError(it)
                })
        )
    }

    fun fetchFiveDaysWeather(lat: String, long: String, units: String) {
        compositeDisposable.addAll(
            userRepository.fivedaysWeather(lat, long, units)
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    fivedaysWeatherData.postValue(it)
                }, {
                    handleNetworkError(it)
                })
        )
    }


}