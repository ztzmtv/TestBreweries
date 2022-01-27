package com.azmetov.breweries.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breweries")
data class BreweryInfoDbModel(

    @PrimaryKey
    val id: String,
    val country: String?,
    val breweryType: String?,
    val city: String?,
    val address: String?,
    val websiteUrl: String?,
    val phone: String?,
    val name: String?,
)
