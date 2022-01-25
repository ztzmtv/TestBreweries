package com.azmetov.breweries.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.azmetov.breweries.data.database.AppDatabase
import com.azmetov.breweries.data.mapper.BreweryMapper
import com.azmetov.breweries.data.network.ApiFactory
import com.azmetov.breweries.domain.BreweriesRepository
import com.azmetov.breweries.domain.BreweryInfo

class BreweriesRepositoryImpl(
    private val application: Application
) : BreweriesRepository {

    private val apiService = ApiFactory.apiService

    private val breweryInfoDao = AppDatabase.getInstance(application).breweryInfoDao()

    private val mapper = BreweryMapper()

    override fun getBreweriesList(): LiveData<List<BreweryInfo>> {
        return Transformations.map(breweryInfoDao.getBreweriesList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getBreweryInfo(id: String): BreweryInfo {
        return mapper.mapDbModelToEntity(
            breweryInfoDao.getBreweryInfo(id)
        )
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
