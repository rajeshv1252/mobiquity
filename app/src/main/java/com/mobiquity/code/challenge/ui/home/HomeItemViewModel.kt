package com.mobiquity.code.challenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.mobiquity.code.challenge.data.model.Location
import com.mobiquity.code.challenge.ui.base.BaseItemViewModel
import com.mobiquity.code.challenge.utils.log.Logger
import com.mobiquity.code.challenge.utils.network.NetworkHelper
import com.mobiquity.code.challenge.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseItemViewModel<Location>(schedulerProvider, compositeDisposable, networkHelper) {

    companion object {
        const val TAG = "HomeItemViewModel"
    }

    val location: LiveData<String> = Transformations.map(data) { it.address }

    val cityData = MutableLiveData<Location>()

    fun onItemClick(position: Int) {
        //messageString.postValue(Resource.success("onItemClick at $position of ${data.value?.address}"))
        Logger.d(TAG, "onItemClick at $position")
        cityData.postValue(data.value)
    }

    override fun onCreate() {
        Logger.d(TAG, "onCreate called")
    }
}