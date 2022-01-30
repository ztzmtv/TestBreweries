package com.azmetov.breweries.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.azmetov.breweries.R
import com.azmetov.breweries.databinding.FragmentBreweriesBinding
import com.azmetov.breweries.domain.BreweryItem
import com.azmetov.breweries.presentation.adapters.BreweryAdapter

class BreweriesFragment : Fragment() {
    private var _binding: FragmentBreweriesBinding? = null
    private val binding: FragmentBreweriesBinding
        get() = _binding ?: throw RuntimeException("FragmentBreweriesBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[BreweriesViewModel::class.java]
    }
    private var id: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreweriesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeId()
        val adapter = BreweryAdapter(requireContext())
        setupAdapter(adapter)
        setupSwipeToDelete(adapter)
    }

    private fun subscribeId() {
        viewModel.breweryItem.observe(viewLifecycleOwner) {
            id = it.id
        }
    }

    override fun onResume() {
        super.onResume()
        log("onResume")
        setupItemInLandMode()
    }

    private fun setupItemInLandMode() {
        log("setupItemInLandMode")
        if (!isOnPortraitMode()) {
            viewModel.breweryItem.observe(viewLifecycleOwner) {
                launchFragmentOnLandscapeMode(it)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        id?.let {
            outState.putString(KEY_ID, id)
        }
    }


    private fun setupAdapter(adapter: BreweryAdapter) {
        adapter.onBreweryClickListener = object : BreweryAdapter.OnBreweryClickListener {
            override fun onBreweryClick(brewery: BreweryItem) {
                if (isOnPortraitMode()) {
                    launchFragmentOnPortraitMode(brewery)
                } else {
                    launchFragmentOnLandscapeMode(brewery)
                }
            }
        }
        binding.rvBreweries.adapter = adapter
        viewModel.breweriesList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setupSwipeToDelete(adapter: BreweryAdapter) {
        val swipeToDeleteCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteBreweryItem(item)
            }

        }
        val swipeToDeleteHelper = ItemTouchHelper(swipeToDeleteCallback)
        swipeToDeleteHelper.attachToRecyclerView(binding.rvBreweries)
    }

    private fun isOnPortraitMode(): Boolean {
        return binding.breweriesFragmentContainerLand == null
    }

    private fun launchFragmentOnLandscapeMode(brewery: BreweryItem) {
        childFragmentManager.beginTransaction()
            .replace(
                R.id.breweries_fragment_container_land,
                BreweryInfoFragment.newInstance(brewery.id)
            )
            .commit()
        log("launchFragmentOnLandscapeMode")
    }

    private fun launchFragmentOnPortraitMode(brewery: BreweryItem) {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.breweries_fragment_container,
                BreweryInfoFragment.newInstance(brewery.id)
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        log("onDestroy")
    }

    private fun log(string: String) {
        Log.d("BreweriesFragment_TAG", string)
    }

    companion object {
        private const val KEY_ID = "id"

        fun newInstance(): Fragment {
            return BreweriesFragment()
        }
    }
}