package com.azmetov.breweries.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.azmetov.breweries.data.network.ApiFactory
import com.azmetov.breweries.data.paging.BreweriesPagingSource
import com.azmetov.breweries.data.repository.BreweriesRepositoryImpl
import com.azmetov.breweries.domain.BreweryInfo
import com.azmetov.breweries.domain.GetBreweriesListUseCase
import com.azmetov.breweries.domain.GetBreweryInfoUseCase
import com.azmetov.breweries.domain.LoadDataUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BreweriesViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = BreweriesRepositoryImpl(application)

    val _app = application

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

    ////////////////////////////////////////////////////////////////////
    private val apiService = ApiFactory.apiService

    fun getBreweriesResultStream(): Flow<PagingData<BreweryInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
//                enablePlaceholders = false
            ),
            pagingSourceFactory = { BreweriesPagingSource(_app, apiService) }
        ).flow
    }


    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}