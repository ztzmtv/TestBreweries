package com.azmetov.breweries.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azmetov.breweries.domain.BreweryInfo
import com.azmetov.breweries.domain.GetBreweriesListUseCase
import com.azmetov.breweries.domain.GetBreweryInfoUseCase
import com.azmetov.breweries.domain.LoadDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class BreweriesViewModel @Inject constructor(
    private val loadDataUseCase: LoadDataUseCase,
    private val getBreweriesListUseCase: GetBreweriesListUseCase,
    private val getBreweryInfoUseCase: GetBreweryInfoUseCase,
    private val _breweryItem: MutableLiveData<BreweryInfo>
) : ViewModel() {

    val breweriesList = getBreweriesListUseCase()

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