package com.mobiquity.code.challenge.di.component

import com.mobiquity.code.challenge.di.ViewModelScope
import com.mobiquity.code.challenge.di.module.ViewHolderModule
import com.mobiquity.code.challenge.ui.home.HomeItemViewHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {
    fun inject(viewHolder: HomeItemViewHolder)

}