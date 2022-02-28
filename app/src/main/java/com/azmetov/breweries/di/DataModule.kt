package com.azmetov.breweries.di

import com.azmetov.breweries.data.repository.BreweriesRepositoryImpl
import com.azmetov.breweries.domain.BreweriesRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: BreweriesRepositoryImpl): BreweriesRepository

}