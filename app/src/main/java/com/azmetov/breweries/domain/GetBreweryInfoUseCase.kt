package com.azmetov.breweries.domain

class GetBreweryInfoUseCase(private val repository: BreweriesRepository) {

    suspend operator fun invoke(id: String): BreweryItem {
        return repository.getBreweryInfo(id)
    }

}