package com.mobiquity.code.challenge.ui.help

import android.os.Bundle
import android.view.View
import com.mobiquity.code.challenge.R
import com.mobiquity.code.challenge.di.component.FragmentComponent
import com.mobiquity.code.challenge.ui.base.BaseFragment

class HelpFragment : BaseFragment<HelpViewModel>() {

    companion object {
        const val TAG = "PhotoFragment"
        fun newInstance(): HelpFragment {
            var args = Bundle()
            val fragment = HelpFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_help

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {

    }

    override fun setupObservers() {
        super.setupObservers()
    }

}