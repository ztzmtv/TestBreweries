package com.azmetov.breweries.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreweryInfoDao {
    @Query("SELECT * FROM breweries ORDER BY name DESC")
    fun getBreweriesList(): List<BreweryInfoDbModel>

    @Query("SELECT * FROM breweries WHERE id == :id LIMIT 1")
    fun getBreweryInfo(id: String): BreweryInfoDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreweriesList(priceList: List<BreweryInfoDbModel>)
}