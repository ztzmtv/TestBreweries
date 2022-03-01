package com.azmetov.breweries.di

import androidx.lifecycle.ViewModel
import com.azmetov.breweries.presentation.BreweriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(value = BreweriesViewModel::class)
    @Binds
    fun bindBreweriesViewModel(impl: BreweriesViewModel): ViewModel
}