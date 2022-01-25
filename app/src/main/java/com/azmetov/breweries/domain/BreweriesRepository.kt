package com.azmetov.breweries.domain

interface BreweriesRepository {

    fun getBreweriesList(): List<BreweryInfo>

    fun getBreweryInfo(id: String): BreweryInfo

    suspend fun loadData()

}