package com.azmetov.breweries.domain

import androidx.lifecycle.LiveData

interface BreweriesRepository {

    fun getBreweriesList(): LiveData<List<BreweryItem>>

    suspend fun getBreweryInfo(id: String): BreweryItem

    suspend fun loadData()

    suspend fun deleteBreweryItem(brewery: BreweryItem)

}