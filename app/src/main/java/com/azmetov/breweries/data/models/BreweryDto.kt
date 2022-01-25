package com.azmetov.breweries.data.models

import com.google.gson.annotations.SerializedName

data class BreweryDto(

	@field:SerializedName("country")
	val country: String?,

	@field:SerializedName("brewery_type")
	val breweryType: String?,

	@field:SerializedName("city")
	val city: String?,

	@field:SerializedName("address_2")
	val address2: Any?,

	@field:SerializedName("latitude")
	val latitude: String?,

	@field:SerializedName("address_3")
	val address3: Any?,

	@field:SerializedName("created_at")
	val createdAt: String?,

	@field:SerializedName("updated_at")
	val updatedAt: String?,

	@field:SerializedName("website_url")
	val websiteUrl: Any?,

	@field:SerializedName("phone")
	val phone: String?,

	@field:SerializedName("street")
	val street: String?,

	@field:SerializedName("name")
	val name: String?,

	@field:SerializedName("county_province")
	val countyProvince: Any?,

	@field:SerializedName("id")
	val id: String?,

	@field:SerializedName("state")
	val state: String?,

	@field:SerializedName("postal_code")
	val postalCode: String?,

	@field:SerializedName("longitude")
	val longitude: String?
)