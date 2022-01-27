package com.azmetov.breweries.domain

import androidx.lifecycle.LiveData

class GetBreweryInfoUseCase(private val repository: BreweriesRepository) {

    operator suspend fun invoke(id: String): BreweryInfo {
        return repository.getBreweryInfo(id)
    }

}