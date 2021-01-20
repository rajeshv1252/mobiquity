package com.mobiquity.code.challenge.di.component

import com.mobiquity.code.challenge.di.ActivityScope
import com.mobiquity.code.challenge.di.module.ActivityModule
import com.mobiquity.code.challenge.ui.city.CityScreen
import com.mobiquity.code.challenge.ui.main.MainActivity
import com.mobiquity.code.challenge.ui.map.MapActivity
import com.mobiquity.code.challenge.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: SplashActivity)

    fun inject(activity: MainActivity)

    fun inject(activity: MapActivity)

    fun inject(activity: CityScreen)
}