package com.azmetov.breweries.domain

data class BreweryInfo(
    val id: String,
    val country: String?,
    val breweryType: String?,
    val city: String?,
    val address: String?,
    val websiteUrl: String?,
    val phone: String?,
    val name: String?,
)