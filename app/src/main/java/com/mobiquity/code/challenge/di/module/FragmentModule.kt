package com.mobiquity.code.challenge.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiquity.code.challenge.data.local.db.DatabaseService
import com.mobiquity.code.challenge.ui.base.BaseFragment
import com.mobiquity.code.challenge.ui.home.HomeAdapter
import com.mobiquity.code.challenge.ui.home.HomeViewModel
import com.mobiquity.code.challenge.ui.main.MainSharedViewModel
import com.mobiquity.code.challenge.ui.help.HelpViewModel
import com.mobiquity.code.challenge.ui.settings.SettingsViewModel
import com.mobiquity.code.challenge.utils.ViewModelProviderFactory
import com.mobiquity.code.challenge.utils.network.NetworkHelper
import com.mobiquity.code.challenge.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)



    @Provides
    fun provideHomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        databaseService: DatabaseService
    ): HomeViewModel =
        ViewModelProviders.of(fragment,
            ViewModelProviderFactory(HomeViewModel::class) {
                HomeViewModel(schedulerProvider, compositeDisposable, networkHelper,databaseService)
            }
        ).get(HomeViewModel::class.java)

    @Provides
    fun provideProfileViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): SettingsViewModel =
        ViewModelProviders.of(fragment,
            ViewModelProviderFactory(SettingsViewModel::class) {
                SettingsViewModel(schedulerProvider, compositeDisposable, networkHelper)
            }
        ).get(SettingsViewModel::class.java)

    @Provides
    fun providePhotoViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): HelpViewModel =
        ViewModelProviders.of(fragment,
            ViewModelProviderFactory(HelpViewModel::class) {
                HelpViewModel(schedulerProvider, compositeDisposable, networkHelper)
            }
        ).get(HelpViewModel::class.java)



    @Provides
    fun provideHomeAdapter() = HomeAdapter(fragment.lifecycle, ArrayList())

    @Provides
    fun provideMainSharedViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): MainSharedViewModel = ViewModelProviders.of(
        fragment.activity!!, ViewModelProviderFactory(MainSharedViewModel::class) {
            MainSharedViewModel(schedulerProvider, compositeDisposable, networkHelper)
        }).get(MainSharedViewModel::class.java)
}