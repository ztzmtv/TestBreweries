package com.azmetov.breweries.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.azmetov.breweries.domain.BreweryInfo

object BreweryItemDiffCallback : DiffUtil.ItemCallback<BreweryInfo>() {
    override fun areItemsTheSame(oldItem: BreweryInfo, newItem: BreweryInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BreweryInfo, newItem: BreweryInfo): Boolean {
        return oldItem == newItem
    }
}