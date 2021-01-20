package com.mobiquity.code.challenge.ui.city

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.mobiquity.code.challenge.R
import com.mobiquity.code.challenge.data.model.CurrentWeather
import com.mobiquity.code.challenge.data.model.FiveDayWeather
import com.mobiquity.code.challenge.data.remote.response.fivedayweather.FiveDayResponse
import com.mobiquity.code.challenge.di.component.ActivityComponent
import com.mobiquity.code.challenge.ui.base.BaseActivity
import com.mobiquity.code.challenge.utils.common.Constants
import com.mobiquity.code.challenge.utils.common.TimeUtils
import kotlinx.android.synthetic.main.activity_city.*
import java.util.*

class CityScreen : BaseActivity<CityViewModel>() {

    companion object {
        val TAG = "City Screen"
    }

    var cityLat = 0.0
    var cityLong = 0.0


    override fun provideLayoutId(): Int = R.layout.activity_city

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        cityLat = intent.getDoubleExtra("lat", 0.0)
        cityLong = intent.getDoubleExtra("long", 0.0)
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.fetchCurrentWeather(cityLat.toString(), cityLong.toString(), Constants.UNITS)
        viewModel.fetchFiveDaysWeather(cityLat.toString(), cityLong.toString(), Constants.UNITS)
        viewModel.currentWeatherData.observe(this, Observer {
            handleCurrentWeather(it)
        })
        viewModel.fivedaysWeatherData.observe(this, Observer {
            handleFiveDaysWeatherResponse(it)
        })
        viewModel.fetchData.observe(this, Observer {
            pb_loading.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun handleCurrentWeather(current_Weather: CurrentWeather) {

        txt_currentWeather.text = current_Weather.toString()

    }


    private fun handleFiveDaysWeatherResponse(fiveDayResponse: FiveDayResponse?) {
        val colors = resources.getIntArray(R.array.mdcolor_500);
        val colorsAlpha = resources.getIntArray(R.array.mdcolor_500_alpha);
        val fiveDaysWeather = ArrayList<FiveDayWeather>()
        val list = fiveDayResponse?.list
        var day = 0
        for (item in list!!) {
            val color = colors[day]
            val colorAlpha = colorsAlpha[day]
            val calendar = Calendar.getInstance(TimeZone.getDefault())
            val newCalendar: Calendar = TimeUtils.addDays(calendar, day)
            var weather = FiveDayWeather(
                item.dt,
                item.main.temp,
                item.main.tempMin,
                item.main.tempMax,
                item.weather[0].id,
                TimeUtils.getStartOfDayTimestamp(newCalendar),
                TimeUtils.getEndOfDayTimestamp(newCalendar),
                color,
                colorAlpha
            )
            fiveDaysWeather.add(weather)
        }
        txt_fiveDaysWeather.text = fiveDaysWeather.toString()
    }
}