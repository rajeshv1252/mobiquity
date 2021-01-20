package com.mobiquity.code.challenge.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiquity.code.challenge.data.local.db.DatabaseService
import com.mobiquity.code.challenge.data.repository.UserRepository
import com.mobiquity.code.challenge.ui.base.BaseActivity
import com.mobiquity.code.challenge.ui.city.CityViewModel
import com.mobiquity.code.challenge.ui.main.MainSharedViewModel
import com.mobiquity.code.challenge.ui.main.MainViewModel
import com.mobiquity.code.challenge.ui.map.MapViewModel
import com.mobiquity.code.challenge.ui.splash.SplashViewModel
import com.mobiquity.code.challenge.utils.ViewModelProviderFactory
import com.mobiquity.code.challenge.utils.network.NetworkHelper
import com.mobiquity.code.challenge.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Kotlin Generics Reference: https://kotlinlang.org/docs/reference/generics.html
 * Basically it means that we can pass any class that extends BaseActivity which take
 * BaseViewModel subclass as parameter
 */
@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideSplashViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): SplashViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(SplashViewModel::class) {
            SplashViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
            //this lambda creates and return SplashViewModel
        }).get(SplashViewModel::class.java)



    @Provides
    fun provideMainViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        databaseService: DatabaseService
    ): MainViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(schedulerProvider, compositeDisposable, networkHelper, databaseService)
        }).get(MainViewModel::class.java)

    @Provides
    fun provideMainSharedViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): MainSharedViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainSharedViewModel::class) {
            MainSharedViewModel(schedulerProvider, compositeDisposable, networkHelper)
        }).get(MainSharedViewModel::class.java)

    @Provides
    fun provideMapViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        databaseService: DatabaseService
    ): MapViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MapViewModel::class) {
            MapViewModel(schedulerProvider, compositeDisposable, networkHelper, databaseService)
        }
    ).get(MapViewModel::class.java)

    @Provides
    fun provideCityViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): CityViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(CityViewModel::class) {
            CityViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
        }
    ).get(CityViewModel::class.java)

}