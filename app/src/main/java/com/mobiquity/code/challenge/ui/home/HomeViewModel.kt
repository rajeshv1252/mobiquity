package com.mobiquity.code.challenge.ui.home


import androidx.lifecycle.MutableLiveData
import com.mobiquity.code.challenge.data.local.db.DatabaseService
import com.mobiquity.code.challenge.ui.base.BaseViewModel
import com.mobiquity.code.challenge.utils.common.Event
import com.mobiquity.code.challenge.utils.network.NetworkHelper
import com.mobiquity.code.challenge.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val databaseService: DatabaseService
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val mapNavigation = MutableLiveData<Event<Boolean>>()

    override fun onCreate() {
    }

    fun onAddClicked() {
        mapNavigation.postValue(Event(true))
    }



}