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

        setFragment()
    }

    private fun setFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.breweries_fragment_container,
                BreweriesFragment.newInstance()
            )
            .commit()
    }
}