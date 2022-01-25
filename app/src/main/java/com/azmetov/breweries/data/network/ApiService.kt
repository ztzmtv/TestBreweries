package com.azmetov.breweries.data.network

import com.azmetov.breweries.data.network.models.BreweryDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //НЕ НАЧИНАТЬ С СИМВОЛА "/"

    @GET("breweries/")
    suspend fun getListOfBreweries(
        @Query(QUERY_PARAM_PER_PAGE) limit: Int = PER_PAGE_DEFAULT,
    ): List<BreweryDto>

    companion object {
        private const val QUERY_PARAM_PER_PAGE = "per_page"
        private const val PER_PAGE_DEFAULT = 15
    }
}