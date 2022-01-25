package com.azmetov.breweries.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.azmetov.breweries.data.repository.BreweriesRepositoryImpl
import com.azmetov.breweries.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class BreweriesViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val repository = BreweriesRepositoryImpl(application)

    private val loadDataUseCase = LoadDataUseCase(repository)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }
}