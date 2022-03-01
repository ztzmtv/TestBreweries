package com.azmetov.breweries.domain

import javax.inject.Inject

class LoadDataUseCase @Inject constructor(private val repository: BreweriesRepository) {

    suspend operator fun invoke() = repository.loadData()

}