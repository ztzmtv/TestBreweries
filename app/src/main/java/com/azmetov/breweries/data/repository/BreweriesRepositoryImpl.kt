package com.azmetov.breweries.data.repository

import android.app.Application
import com.azmetov.breweries.data.network.ApiFactory
import com.azmetov.breweries.data.database.AppDatabase
import com.azmetov.breweries.data.mapper.BreweryMapper
import com.azmetov.breweries.domain.BreweriesRepository
import com.azmetov.breweries.domain.BreweryInfo

class BreweriesRepositoryImpl(
    private val application: Application
) : BreweriesRepository {

    private val apiService = ApiFactory.apiService

    private val breweryInfoDao = AppDatabase.getInstance(application).breweryInfoDao()

    private val mapper = BreweryMapper()

    override fun getBreweriesList(): List<BreweryInfo> {
        TODO("Not yet implemented")
    }

    override fun getBreweryInfo(id: String): BreweryInfo {
        TODO("Not yet implemented")
    }

    override suspend fun loadData() {
        try {
            val breweriesListDto = apiService.getListOfBreweries(10)
            val breweryInfoDbModel = breweriesListDto.map {
                mapper.mapDtoToDbModel(it)
            }
            breweryInfoDao.insertBreweriesList(breweryInfoDbModel)
        } catch (e: Exception) {
        }


    }
}
