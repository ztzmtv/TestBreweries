package com.azmetov.breweries.data.mapper

import com.azmetov.breweries.data.database.BreweryInfoDbModel
import com.azmetov.breweries.data.network.models.BreweryDto
import com.azmetov.breweries.domain.BreweryInfo

class BreweryMapper {

    fun mapDtoToDbModel(dto: BreweryDto) = BreweryInfoDbModel(
        id = dto.id,
        country = dto.country,
        breweryType = dto.breweryType,
        city = dto.city,
        address = dto.address,
        websiteUrl = dto.websiteUrl,
        phone = dto.phone,
        name = dto.name
    )

    fun mapDbModelToEntity(dbModel: BreweryInfoDbModel) = BreweryInfo(
        id = dbModel.id,
        country = dbModel.country,
        breweryType = dbModel.breweryType,
        city = dbModel.city,
        address = dbModel.address,
        websiteUrl = dbModel.websiteUrl,
        phone = dbModel.phone,
        name = dbModel.name
    )
}