package com.mobiquity.code.challenge.di.component

import com.mobiquity.code.challenge.di.FragmentScope
import com.mobiquity.code.challenge.di.module.FragmentModule
import com.mobiquity.code.challenge.ui.home.HomeFragment
import com.mobiquity.code.challenge.ui.help.HelpFragment
import com.mobiquity.code.challenge.ui.settings.SettingsFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {


    fun inject(fragment: HomeFragment)

    fun inject(fragment: SettingsFragment)

    fun inject(fragment: HelpFragment)
}