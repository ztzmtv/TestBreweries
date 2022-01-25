package com.azmetov.breweries.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azmetov.breweries.data.repository.BreweriesRepositoryImpl
import com.azmetov.breweries.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class BreweriesViewModel : ViewModel() {
    private val repository = BreweriesRepositoryImpl()

    private val loadDataUseCase = LoadDataUseCase(repository)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }
}