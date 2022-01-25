package com.azmetov.breweries.domain

class GetBreweryInfoUseCase(private val repository: BreweriesRepository) {

    suspend operator fun invoke(id: String): BreweryInfo {
        return repository.getBreweryInfo(id)
    }

}