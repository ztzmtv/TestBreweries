package com.azmetov.breweries.domain

import androidx.lifecycle.LiveData

interface BreweriesRepository {

    fun getBreweriesList(): LiveData<List<BreweryInfo>>

    fun getBreweryInfo(id: String): LiveData<BreweryInfo>

    suspend fun loadData()

}