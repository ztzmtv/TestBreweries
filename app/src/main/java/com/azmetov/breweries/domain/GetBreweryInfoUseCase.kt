package com.azmetov.breweries.domain

import androidx.lifecycle.LiveData

class GetBreweryInfoUseCase(private val repository: BreweriesRepository) {

    operator fun invoke(id: String): LiveData<BreweryInfo> {
        return repository.getBreweryInfo(id)
    }

}