package com.mobiquity.code.challenge.di.component

import com.mobiquity.code.challenge.di.module.ApplicationTestModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationTestModule::class])
interface TestComponent : ApplicationComponent {
}