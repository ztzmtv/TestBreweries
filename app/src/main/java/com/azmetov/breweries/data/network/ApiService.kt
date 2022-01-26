package com.azmetov.breweries.data.network

import com.azmetov.breweries.data.network.models.BreweryDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //НЕ НАЧИНАТЬ С СИМВОЛА "/"

    @GET("breweries/")
    suspend fun getListOfBreweries(
        @Query(QUERY_PARAM_PER_PAGE) limit: Int = PER_PAGE_DEFAULT,
    ): List<BreweryDto>

    @GET("breweries/{id}")
    suspend fun getBreweryInfo(
        @Path(QUERY_PATH_ID) id: String = ID_DEFAULT,
    ): BreweryDto

    companion object {
        private const val QUERY_PATH_ID = "id"
        private const val ID_DEFAULT = "10-barrel-brewing-co-denver-denver"
        private const val QUERY_PARAM_PER_PAGE = "per_page"
        private const val PER_PAGE_DEFAULT = 15
    }
}