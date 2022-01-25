package com.azmetov.breweries.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.azmetov.breweries.R

class BreweriesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this)[BreweriesViewModel::class.java]
        viewModel.breweriesList.observe(this) {
            Log.d("BreweriesListActivity_TAG", it.toString())
        }


    }
}