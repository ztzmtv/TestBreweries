package com.azmetov.breweries.domain

class DeleteBreweryItemUseCase(private val repository: BreweriesRepository) {

    suspend operator fun invoke(brewery: BreweryItem) {
        repository.deleteBreweryItem(brewery)
    }
}