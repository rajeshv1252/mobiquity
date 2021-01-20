package com.mobiquity.code.challenge.ui.settings

import android.os.Bundle
import android.view.View
import com.mobiquity.code.challenge.R
import com.mobiquity.code.challenge.di.component.FragmentComponent
import com.mobiquity.code.challenge.ui.base.BaseFragment

class SettingsFragment : BaseFragment<SettingsViewModel>() {

    companion object {
        const val TAG = "ProfileFragment"
        fun newInstance(): SettingsFragment {
            var args = Bundle()
            val fragment = SettingsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_settings

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {

    }

    override fun setupObservers() {
        super.setupObservers()
    }

}