package com.mobiquity.code.challenge.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiquity.code.challenge.R
import com.mobiquity.code.challenge.di.component.FragmentComponent
import com.mobiquity.code.challenge.ui.base.BaseFragment
import com.mobiquity.code.challenge.ui.main.MainSharedViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModel>() {

    companion object {
        const val TAG = "HomeFragment"
        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var homeAdapter: HomeAdapter

    @Inject
    lateinit var mainSharedViewModel: MainSharedViewModel

    override fun provideLayoutId(): Int = R.layout.fragment_home

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {
        rv_locations.layoutManager = linearLayoutManager
        rv_locations.adapter = homeAdapter
        addLocation.setOnClickListener {
            showMessage("onAddClicked")
            viewModel.onAddClicked()
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.mapNavigation.observe(this, Observer {
            it.getIfNotHandled()?.run {
                Log.e("TAG", "OnHomeRedirect")
                mainSharedViewModel.onHomeRedirect()
            }
        })

        mainSharedViewModel.homeData.observe(this, Observer {
            it?.run {
                homeAdapter.clear()
                homeAdapter.appendData(this)
            }
        })
    }
}