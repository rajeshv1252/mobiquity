package com.mobiquity.code.challenge.data.remote

import com.mobiquity.code.challenge.data.remote.response.currentweather.CurrentWeatherResponse
import com.mobiquity.code.challenge.data.remote.response.fivedayweather.FiveDayResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface NetworkService {

    @GET(Endpoints.WEATHER)
    fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): Single<CurrentWeatherResponse>

    @GET("forecast")
    fun getFiveDaysWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): Single<FiveDayResponse>
}