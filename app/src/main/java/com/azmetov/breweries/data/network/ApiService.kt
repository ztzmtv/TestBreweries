package com.azmetov.breweries.data.network

import com.azmetov.breweries.data.network.models.BreweryDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //НЕ НАЧИНАТЬ С СИМВОЛА "/"

    @GET("breweries/")
    suspend fun getListOfBreweries(
        @Query(QUERY_PARAM_PER_PAGE) per_page: Int = DEFAULT_PER_PAGE,
        @Query(QUERY_PARAM_PAGE) page: Int = DEFAULT_PAGE
    ): List<BreweryDto>

//    @GET("breweries/{id}")
//    suspend fun getBreweryInfo(
//        @Path(QUERY_PATH_ID) id: String = DEFAULT_ID,
//    ): BreweryDto

    companion object {
        private const val QUERY_PARAM_PER_PAGE = "per_page"
        private const val DEFAULT_PER_PAGE = 15
        private const val QUERY_PARAM_PAGE = "page"
        private const val DEFAULT_PAGE = 1
    }
}