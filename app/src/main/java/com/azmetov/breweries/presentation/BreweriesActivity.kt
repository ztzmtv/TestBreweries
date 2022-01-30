package com.azmetov.breweries.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.azmetov.breweries.R
import com.azmetov.breweries.databinding.ActivityBreweriesBinding

class BreweriesActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityBreweriesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        savedInstanceState ?: fragmentSetup()
        log("savedInstanceState $savedInstanceState")
    }

    private fun fragmentSetup() {
        val portFragment =
            supportFragmentManager.findFragmentById(R.id.breweries_fragment_container)
        val landFragment =
            supportFragmentManager.findFragmentById(R.id.breweries_fragment_container_land)
        if (landFragment == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.breweries_fragment_container,
                    BreweriesFragment.newInstance()
                )
                .commit()
        } else if (portFragment == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.breweries_fragment_container_land,
                    BreweriesFragment()
                )
                .commit()
        }
    }

    private fun log(string: String) {
        Log.d("BreweriesActivity_TAG", string)
    }
}