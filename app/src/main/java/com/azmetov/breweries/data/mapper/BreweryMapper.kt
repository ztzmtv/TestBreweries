package com.azmetov.breweries.data.mapper

import com.azmetov.breweries.data.models.BreweryDto
import com.azmetov.breweries.domain.BreweryInfo

class BreweryMapper {

    // temporary
    fun mapDbModelToEntity(dto: BreweryDto) = BreweryInfo(
        country = dto.country,
        breweryType = dto.breweryType,
        city = dto.city,
        address2 = dto.address2,
        websiteUrl = dto.websiteUrl,
        phone = dto.phone,
        name = dto.name,
        id = dto.id
    )
}