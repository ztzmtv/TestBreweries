package com.azmetov.breweries.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.azmetov.breweries.data.repository.BreweriesRepositoryImpl
import com.azmetov.breweries.domain.*
import kotlinx.coroutines.launch

class BreweriesViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val repository = BreweriesRepositoryImpl(application)

    private val loadDataUseCase = LoadDataUseCase(repository)
    private val getBreweriesListUseCase = GetBreweriesListUseCase(repository)
    private val getBreweryInfoUseCase = GetBreweryInfoUseCase(repository)
    private val deleteBreweryItemUseCase = DeleteBreweryItemUseCase(repository)

    val breweriesList = getBreweriesListUseCase()

    private val _breweryItem = MutableLiveData<BreweryItem>()
    val breweryItem: LiveData<BreweryItem>
        get() = _breweryItem

    fun getBreweryInfo(id: String) {
        viewModelScope.launch {
            val item = getBreweryInfoUseCase(id)
            _breweryItem.value = item
            log("getBreweryInfo ${_breweryItem.value}")
        }
    }

    fun deleteBreweryItem(breweryItem: BreweryItem) {
        viewModelScope.launch {
            deleteBreweryItemUseCase(breweryItem)
        }
    }

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }

    private fun log(string: String) {
        Log.d("BreweriesViewModel_TAG", string)
    }
}