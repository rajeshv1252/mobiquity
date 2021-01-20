package com.mobiquity.code.challenge.ui.home

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.mobiquity.code.challenge.data.model.Location
import com.mobiquity.code.challenge.ui.base.BaseAdapter

class HomeAdapter(
    parentLifecycle: Lifecycle,
    private val locations: ArrayList<Location>
) : BaseAdapter<Location, HomeItemViewHolder>(parentLifecycle, locations) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeItemViewHolder(parent)
}