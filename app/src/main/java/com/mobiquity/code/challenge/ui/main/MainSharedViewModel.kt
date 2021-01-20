package com.mobiquity.code.challenge.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mobiquity.code.challenge.data.model.Location
import com.mobiquity.code.challenge.ui.base.BaseViewModel
import com.mobiquity.code.challenge.utils.common.Event
import com.mobiquity.code.challenge.utils.network.NetworkHelper
import com.mobiquity.code.challenge.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainSharedViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    override fun onCreate() {}

    val mapRedirection = MutableLiveData<Event<Boolean>>()

    val homeData = MutableLiveData<List<Location>>()

    fun onHomeRedirect() {
        Log.e("TAG", "SharedViewModel")
        mapRedirection.postValue(Event(true))
    }
}