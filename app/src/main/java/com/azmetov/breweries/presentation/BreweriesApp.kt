package com.azmetov.breweries.presentation

import android.app.Application
import com.azmetov.breweries.di.DaggerApplicationComponent

class BreweriesApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}