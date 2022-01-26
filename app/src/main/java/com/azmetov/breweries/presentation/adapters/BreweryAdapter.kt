package com.azmetov.breweries.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.azmetov.breweries.R
import com.azmetov.breweries.databinding.BreweryItemBinding
import com.azmetov.breweries.domain.BreweryInfo

class BreweryAdapter(
    private val context: Context
) : ListAdapter<BreweryInfo, BreweryViewHolder>(BreweryItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreweryViewHolder {
        val binding =
            BreweryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return BreweryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreweryViewHolder, position: Int) {
        val brewery = getItem(position)
        with(holder.binding) {
            val breweryNameTemplate =
                context.resources.getString(R.string.brewery_name_template)
            tvBreweryName.text = String.format(breweryNameTemplate, brewery.name)
            val breweryTypeTemplate =
                context.resources.getString(R.string.brewery_type_template)
            tvBreweryType.text = String.format(breweryTypeTemplate, brewery.breweryType)
            val breweryCountryTemplate =
                context.resources.getString(R.string.brewery_country_template)
            tvBreweryType.text = String.format(breweryCountryTemplate, brewery.country)
        }
    }
}