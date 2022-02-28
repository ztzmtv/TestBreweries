package com.azmetov.breweries.di

import android.app.Application
import com.azmetov.breweries.presentation.BreweriesFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class])
interface ApplicationComponent {

    fun inject(fragment: BreweriesFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}