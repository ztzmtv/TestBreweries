package com.azmetov.breweries.data.repository

import android.util.Log
import com.azmetov.breweries.data.ApiFactory
import com.azmetov.breweries.data.mapper.BreweryMapper
import com.azmetov.breweries.domain.BreweriesRepository
import com.azmetov.breweries.domain.BreweryInfo

class BreweriesRepositoryImpl : BreweriesRepository {

    private val apiService = ApiFactory.apiService

    private val mapper = BreweryMapper()

    override fun getBreweriesList(): List<BreweryInfo> {
        TODO("Not yet implemented")
    }

    override fun getBreweryInfo(id: String): BreweryInfo {
        TODO("Not yet implemented")
    }

    override suspend fun loadData() {
        val breweriesListDto = apiService.getListOfBreweries(2)
        Log.d("BreweriesRepositoryImpl", "$breweriesListDto")

    }
}
