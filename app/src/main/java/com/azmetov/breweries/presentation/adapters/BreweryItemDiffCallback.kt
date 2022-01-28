package com.azmetov.breweries.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.azmetov.breweries.domain.BreweryItem

object BreweryItemDiffCallback : DiffUtil.ItemCallback<BreweryItem>() {
    override fun areItemsTheSame(oldItem: BreweryItem, newItem: BreweryItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BreweryItem, newItem: BreweryItem): Boolean {
        return oldItem == newItem
    }
}