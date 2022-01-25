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


/*
class CoinRepositoryImpl(
    private val application: Application
) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()

    private val mapper = CoinMapper()

    private val apiService = ApiFactory.apiService

    override fun getCoinInfo(fSym: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fSym)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getPriceList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins =
                    apiService.getTopCoinsInfo(limit = 50)

                val fSym =
                    mapper.mapNamesListToString(topCoins) // преобразуем в строку через запятую

                val jsonContainer =
                    apiService.getFullPriceList(fSyms = fSym) // получаем полный прайслист из сети

                val coinInfoDtoList =
                    mapper.mapJsonContainerToListCoinInfo(jsonContainer) // json -> Dto

                val dbModelList =
                    coinInfoDtoList.map { mapper.mapDtoToDbModel(it) } // Dto -> DbModel

                coinInfoDao.insertPriceList(dbModelList) // передаем в БД}

            } catch (e: Exception) {
            }
            delay(10000)
        }
    }
    companion object{
        private const val TAG = "CoinRepoImpl_TAG"

        private fun log(string: String){
            Log.d(TAG, string)
        }
    }

}*/
