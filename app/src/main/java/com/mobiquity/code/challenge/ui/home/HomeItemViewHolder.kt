package com.mobiquity.code.challenge.ui.home

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.mobiquity.code.challenge.R
import com.mobiquity.code.challenge.data.model.Location
import com.mobiquity.code.challenge.di.component.ViewHolderComponent
import com.mobiquity.code.challenge.ui.base.BaseItemViewHolder
import com.mobiquity.code.challenge.ui.city.CityScreen
import kotlinx.android.synthetic.main.item_view_locations.view.*

class HomeItemViewHolder(val parent: ViewGroup) :
    BaseItemViewHolder<Location, HomeItemViewModel>(R.layout.item_view_locations, parent) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.location.observe(this, Observer {
            itemView.tv_head_location.text = it
        })

        viewModel.cityData.observe(this, Observer {
            navigateToCityScreen(it)
        })
    }

    override fun setupView(view: View) {
        view.setOnClickListener {
            viewModel.onItemClick(adapterPosition)
        }
    }

    private fun navigateToCityScreen(location: Location) {
        val cityIntent = Intent(parent.context, CityScreen::class.java)
        cityIntent.putExtra("lat", location.latitude)
        cityIntent.putExtra("long", location.longitude)
        parent.context.startActivity(cityIntent)
    }
}