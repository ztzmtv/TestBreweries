package com.azmetov.breweries.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetBreweryInfoUseCase @Inject constructor(private val repository: BreweriesRepository) {

    suspend operator fun invoke(id: String): BreweryInfo {
        return repository.getBreweryInfo(id)
    }

}