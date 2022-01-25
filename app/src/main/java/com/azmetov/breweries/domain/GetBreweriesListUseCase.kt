package com.azmetov.breweries.domain

import androidx.lifecycle.LiveData

class GetBreweriesListUseCase(private val repository: BreweriesRepository) {

    operator fun invoke(): LiveData<List<BreweryInfo>> {
        return repository.getBreweriesList()
    }

}