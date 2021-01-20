package com.mobiquity.code.challenge.data.repository

import com.mobiquity.code.challenge.BuildConfig
import com.mobiquity.code.challenge.data.local.db.DatabaseService
import com.mobiquity.code.challenge.data.model.CurrentWeather
import com.mobiquity.code.challenge.data.remote.NetworkService
import com.mobiquity.code.challenge.data.remote.response.fivedayweather.FiveDayResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {


    fun currentWeather(lat: String, long: String, units: String): Single<CurrentWeather> =
        networkService.getCurrentWeather(lat, long, units, BuildConfig.API_KEY).map {
            CurrentWeather(
                it.main.temp,
                it.main.humidity,
                it.weather[0].description,
                it.weather[0].main,
                it.weather[0].id,
                it.wind.deg,
                it.wind.speed,
                System.currentTimeMillis()
            )
        }

    fun fivedaysWeather(lat: String, long: String, units: String): Single<FiveDayResponse> =
        networkService.getFiveDaysWeather(lat, long, units, BuildConfig.API_KEY)
}