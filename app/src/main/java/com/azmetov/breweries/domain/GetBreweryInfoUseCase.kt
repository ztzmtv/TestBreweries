package com.azmetov.breweries.domain

class GetBreweryInfoUseCase(private val repository: BreweriesRepository) {

    operator fun invoke(id: String): BreweryInfo {
        return repository.getBreweryInfo(id)
    }

}