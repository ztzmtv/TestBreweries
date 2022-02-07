package com.azmetov.breweries.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.azmetov.breweries.data.mapper.BreweryMapper
import com.azmetov.breweries.data.network.ApiService
import com.azmetov.breweries.domain.BreweryInfo
import retrofit2.HttpException
import java.io.IOException

class BreweriesPagingSource(
    private val service: ApiService
) : PagingSource<Int, BreweryInfo>() {
    override fun getRefreshKey(state: PagingState<Int, BreweryInfo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BreweryInfo> {
        val mapper = BreweryMapper()
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getListOfBreweries(
                per_page = DEFAULT_PER_PAGE,
                page = pageIndex
            )
            val breweries = mutableListOf<BreweryInfo>()
            response.forEach { breweries.add(mapper.mapDtoToEntity(it)) }
            val nextKey =
                if (breweries.isEmpty()) {
                    null
                } else {
                    pageIndex + (params.loadSize / DEFAULT_PER_PAGE)
                }
            LoadResult.Page(
                data = breweries,
                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex,
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
        private const val DEFAULT_PER_PAGE = 20
    }
}