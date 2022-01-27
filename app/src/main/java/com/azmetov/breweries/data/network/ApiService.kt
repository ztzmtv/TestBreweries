package com.azmetov.breweries.data.network

import com.azmetov.breweries.data.network.models.BreweryDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //НЕ НАЧИНАТЬ С СИМВОЛА "/"

    @GET("breweries/")
    suspend fun getListOfBreweries(
        @Query(QUERY_PARAM_PER_PAGE) limit: Int = DEFAULT_PER_PAGE,
    ): List<BreweryDto>

    @GET("breweries/{id}")
    suspend fun getBreweryInfo(
        @Path(QUERY_PATH_ID) id: String = DEFAULT_ID,
    ): BreweryDto

    companion object {
        private const val QUERY_PATH_ID = "id"
        private const val QUERY_PARAM_PER_PAGE = "per_page"
        private const val DEFAULT_ID = "10-barrel-brewing-co-denver-denver"
        private const val DEFAULT_PER_PAGE = 15
    }
}