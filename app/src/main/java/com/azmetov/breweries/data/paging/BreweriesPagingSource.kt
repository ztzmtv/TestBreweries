package com.azmetov.breweries.data.paging

import android.app.Application
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.azmetov.breweries.data.database.AppDatabase
import com.azmetov.breweries.data.mapper.BreweryMapper
import com.azmetov.breweries.data.network.ApiService
import com.azmetov.breweries.domain.BreweryInfo
import retrofit2.HttpException
import java.io.IOException

class BreweriesPagingSource(
    application: Application,
    private val service: ApiService
) : PagingSource<Int, BreweryInfo>() {

    private val breweryInfoDao = AppDatabase.getInstance(application).breweryInfoDao()

    override fun getRefreshKey(state: PagingState<Int, BreweryInfo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BreweryInfo> {
        val mapper = BreweryMapper()
        val pageNumber = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getListOfBreweries(
                per_page = DEFAULT_PER_PAGE,
                page = pageNumber
            )
            val breweries = mutableListOf<BreweryInfo>()
            val breweryInfoDbModel = response.map {
                mapper.mapDtoToDbModel(it)
            }
            breweryInfoDao.insertBreweriesList(breweryInfoDbModel)

            response.forEach { breweries.add(mapper.mapDtoToEntity(it)) }
            val nextKey =
                if (breweries.isEmpty()) {
                    null
                } else {
                    pageNumber + (params.loadSize / DEFAULT_PER_PAGE)
                }
            LoadResult.Page(
                data = breweries,
                prevKey = if (pageNumber == STARTING_PAGE_INDEX) null else pageNumber,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
        private const val DEFAULT_PER_PAGE = 10
    }
}