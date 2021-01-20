package com.mobiquity.code.challenge.di.module

import androidx.lifecycle.LifecycleRegistry
import com.mobiquity.code.challenge.di.ViewModelScope
import com.mobiquity.code.challenge.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)

    
}