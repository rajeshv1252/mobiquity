package com.mobiquity.code.challenge.ui.main

import androidx.lifecycle.MutableLiveData
import com.mobiquity.code.challenge.data.local.db.DatabaseService
import com.mobiquity.code.challenge.data.model.Location
import com.mobiquity.code.challenge.ui.base.BaseViewModel
import com.mobiquity.code.challenge.utils.common.Event
import com.mobiquity.code.challenge.utils.log.Logger
import com.mobiquity.code.challenge.utils.network.NetworkHelper
import com.mobiquity.code.challenge.utils.rx.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val databaseService: DatabaseService
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    companion object {
        const val TAG = "MainViewModel"
    }

    val profileNavigation = MutableLiveData<Event<Boolean>>()
    val homeNavigation = MutableLiveData<Event<Boolean>>()
    val photoNavigation = MutableLiveData<Event<Boolean>>()
    val allUser = MutableLiveData<List<Location>>()

    init {
        getLocations()
    }

    override fun onCreate() {
        homeNavigation.postValue(Event(true))
    }

    fun onProfileSelected() {
        profileNavigation.postValue(Event(true))
    }

    fun onHomeSelected() {
        homeNavigation.postValue(Event(true))
    }

    fun onPhotoSelected() {
        photoNavigation.postValue(Event(true))
    }

    fun getLocations() {
        compositeDisposable.add(
            databaseService.locationDao()
                .getAllLocations()
                .flatMap { list ->
                    Observable.fromIterable(list)
                        .map { item ->
                            Location(item.address, item.latitude, item.longitude)
                        }.toList()
                }
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    allUser.postValue(it)
                }, {
                    Logger.e(MainViewModel.TAG, it.toString())
                })
        )
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }

}