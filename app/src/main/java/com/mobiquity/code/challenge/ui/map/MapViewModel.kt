package com.mobiquity.code.challenge.ui.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.Marker
import com.mobiquity.code.challenge.data.local.db.DatabaseService
import com.mobiquity.code.challenge.data.local.db.entity.LocationEntity
import com.mobiquity.code.challenge.ui.base.BaseViewModel
import com.mobiquity.code.challenge.utils.common.Event
import com.mobiquity.code.challenge.utils.network.NetworkHelper
import com.mobiquity.code.challenge.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MapViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val databaseService: DatabaseService
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    val location = MutableLiveData<Event<Boolean>>()

    companion object {
        const val TAG = "MapViewModel"
    }

    override fun onCreate() {
    }

    fun saveLocation(marker: Marker) {
        compositeDisposable.add(
            databaseService.locationDao()
                .insert(
                    LocationEntity(
                        address = marker.snippet, latitude = marker.position.latitude
                        , longitude = marker.position.longitude
                    )
                )
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    Log.e(TAG,"inserted "+it)
                    location.postValue(Event(true))
                }, {
                    Log.d(TAG, it.toString())
                })
        )
    }


    fun onDestroy() {
        compositeDisposable.clear()
    }
}