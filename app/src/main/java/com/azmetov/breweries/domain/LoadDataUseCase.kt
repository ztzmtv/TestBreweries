package com.azmetov.breweries.domain

class LoadDataUseCase(private val repository: BreweriesRepository) {

    suspend operator fun invoke() = repository.loadData()

}