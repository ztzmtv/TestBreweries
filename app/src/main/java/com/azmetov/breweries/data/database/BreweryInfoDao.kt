package com.azmetov.breweries.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreweryInfoDao {

    @Query("SELECT * FROM breweries ORDER BY name DESC")
    fun getBreweriesList(): LiveData<List<BreweryInfoDbModel>>

    @Query("SELECT * FROM breweries WHERE id == :id LIMIT 1")
    suspend fun getBreweryInfo(id: String): BreweryInfoDbModel

    @Query("DELETE FROM breweries WHERE id=:id")
    suspend fun deleteBreweryItem(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreweriesList(priceList: List<BreweryInfoDbModel>)

}