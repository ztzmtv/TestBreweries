package com.azmetov.breweries.domain

class GetBreweriesListUseCase(private val repository: BreweriesRepository) {

    operator fun invoke(): List<BreweryInfo> {
        return repository.getBreweriesList()
    }

}