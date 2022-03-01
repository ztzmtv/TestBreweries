package com.azmetov.breweries.di

import android.app.Application
import com.azmetov.breweries.presentation.BreweriesFragment
import com.azmetov.breweries.presentation.BreweryInfoFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(fragment: BreweriesFragment)

    fun inject(fragment: BreweryInfoFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}