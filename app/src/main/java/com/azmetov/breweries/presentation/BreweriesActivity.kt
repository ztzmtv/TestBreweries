package com.azmetov.breweries.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.azmetov.breweries.databinding.ActivityBreweriesBinding
import com.azmetov.breweries.presentation.adapters.BreweryAdapter

class BreweriesActivity : AppCompatActivity() {

    val viewModel by lazy {
        ViewModelProvider(this)[BreweriesViewModel::class.java]
    }
    private val binding by lazy {
        ActivityBreweriesBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = BreweryAdapter(this)
        binding.rvBreweries.adapter = adapter
        viewModel.breweriesList.observe(this) {
            adapter.submitList(it)
            log(it.toString())

        }

    }

    private fun log(string: String) {
        Log.d(TAG, string)
    }

    companion object {
        private const val TAG = "BreweriesActivity_TAG"

    }
}