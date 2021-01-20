package com.mobiquity.code.challenge

import android.app.Application
import com.mobiquity.code.challenge.di.component.ApplicationComponent
import com.mobiquity.code.challenge.di.component.DaggerApplicationComponent
import com.mobiquity.code.challenge.di.module.ApplicationModule

class WeatherApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    // Needed to replace the component with a test specific one
    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }
}