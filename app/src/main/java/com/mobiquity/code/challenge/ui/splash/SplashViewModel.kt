package com.mobiquity.code.challenge.ui.splash

import androidx.lifecycle.MutableLiveData
import com.mobiquity.code.challenge.data.repository.UserRepository
import com.mobiquity.code.challenge.ui.base.BaseViewModel
import com.mobiquity.code.challenge.utils.common.Event
import com.mobiquity.code.challenge.utils.network.NetworkHelper
import com.mobiquity.code.challenge.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class SplashViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    // Event is used by the view model to tell the activity to launch another Activity
    // view model also provided the Bundle in the event that is needed for the Activity
    val launchMain: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    override fun onCreate() {
            launchMain.postValue(Event(emptyMap()))

    }
}