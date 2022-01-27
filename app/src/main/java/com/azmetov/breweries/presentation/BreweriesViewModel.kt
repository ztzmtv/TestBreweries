package com.azmetov.breweries.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.azmetov.breweries.data.repository.BreweriesRepositoryImpl
import com.azmetov.breweries.domain.BreweryInfo
import com.azmetov.breweries.domain.GetBreweriesListUseCase
import com.azmetov.breweries.domain.GetBreweryInfoUseCase
import com.azmetov.breweries.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class BreweriesViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val repository = BreweriesRepositoryImpl(application)

    private val loadDataUseCase = LoadDataUseCase(repository)
    private val getBreweriesListUseCase = GetBreweriesListUseCase(repository)
    private val getBreweryInfoUseCase = GetBreweryInfoUseCase(repository)

    val breweriesList = getBreweriesListUseCase()

    private val _breweryItem = MutableLiveData<BreweryInfo>()
    val breweryItem: LiveData<BreweryInfo>
        get() = _breweryItem

    fun getBreweryInfo(id: String) {
        viewModelScope.launch {
            val item = getBreweryInfoUseCase(id)
            _breweryItem.value = item
        }
    }

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }


}