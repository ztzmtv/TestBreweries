package com.azmetov.breweries.data.network.models

import com.google.gson.annotations.SerializedName

data class BreweriesListDto(

	@field:SerializedName("BreweriesDbModel")
	val brewery: List<BreweryDto?>?
)