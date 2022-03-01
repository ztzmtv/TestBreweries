package com.azmetov.breweries.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetBreweriesListUseCase @Inject constructor(private val repository: BreweriesRepository) {

    operator fun invoke(): LiveData<List<BreweryInfo>> {
        return repository.getBreweriesList()
    }

}