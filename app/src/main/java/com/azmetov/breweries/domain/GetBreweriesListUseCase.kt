package com.azmetov.breweries.domain

import androidx.lifecycle.LiveData

class GetBreweriesListUseCase(private val repository: BreweriesRepository) {

    operator fun invoke(): LiveData<List<BreweryItem>> {
        return repository.getBreweriesList()
    }

}