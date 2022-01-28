package com.azmetov.breweries.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.azmetov.breweries.data.database.AppDatabase
import com.azmetov.breweries.data.mapper.BreweryMapper
import com.azmetov.breweries.data.network.ApiFactory
import com.azmetov.breweries.domain.BreweriesRepository
import com.azmetov.breweries.domain.BreweryItem

class BreweriesRepositoryImpl(
    private val application: Application
) : BreweriesRepository {

    private val apiService = ApiFactory.apiService

    private val breweryInfoDao = AppDatabase.getInstance(application).breweryInfoDao()

    private val mapper = BreweryMapper()

    override fun getBreweriesList(): LiveData<List<BreweryItem>> {
        return Transformations.map(breweryInfoDao.getBreweriesList()) { ListOfBreweryInfo ->
            ListOfBreweryInfo.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override suspend fun getBreweryInfo(id: String): BreweryItem {
        return mapper.mapDbModelToEntity(
            breweryInfoDao.getBreweryInfo(id)
        )
    }

    override suspend fun loadData() {
        try {
            val breweriesListDto = apiService.getListOfBreweries(NUMBER_OF_LOADING_ITEMS)
            val breweryInfoDbModel = breweriesListDto.map {
                mapper.mapDtoToDbModel(it)
            }
            breweryInfoDao.insertBreweriesList(breweryInfoDbModel)
        } catch (e: Exception) {
        }
    }

    override suspend fun deleteBreweryItem(brewery: BreweryItem) {
        breweryInfoDao.deleteBreweryItem(brewery.id)
    }

    companion object {
        private const val NUMBER_OF_LOADING_ITEMS = 50
    }
}
