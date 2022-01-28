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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreweriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        log("onDestroy")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BreweryAdapter(requireContext())
        adapter.onBreweryClickListener = object : BreweryAdapter.OnBreweryClickListener {
            override fun onBreweryClick(brewery: BreweryItem) {
                if (isOnPaneMode()) {
                    launchFragmentOnPaneMode(brewery)
                } else {
                    launchFragmentOnLandMode(brewery)
                }
            }
        }
        binding.rvBreweries.adapter = adapter
        viewModel.breweriesList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

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

    private fun isOnPaneMode(): Boolean {
        return binding.breweriesFragmentContainerLand == null
    }

    private fun launchFragmentOnLandMode(brewery: BreweryItem) {
        childFragmentManager.beginTransaction()
            .replace(
                R.id.breweries_fragment_container_land,
                BreweryInfoFragment.newInstance(brewery.id)
            )
            .commit()
    }

    private fun launchFragmentOnPaneMode(brewery: BreweryItem) {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.breweries_fragment_container,
                BreweryInfoFragment.newInstance(brewery.id)
            )
            .addToBackStack(null)
            .commit()
    }

    private fun log(string: String) {
        Log.d("TAG", string)
    }

    companion object {
        fun newInstance(): Fragment {
            return BreweriesFragment()
        }
    }
}