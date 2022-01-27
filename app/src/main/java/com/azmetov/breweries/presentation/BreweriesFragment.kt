package com.azmetov.breweries.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.azmetov.breweries.R
import com.azmetov.breweries.databinding.FragmentBreweriesBinding
import com.azmetov.breweries.domain.BreweryInfo
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BreweryAdapter(requireContext())
        adapter.onBreweryClickListener = object : BreweryAdapter.OnBreweryClickListener {
            override fun onBreweryClick(brewery: BreweryInfo) {
                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.breweries_fragment_container,
                        BreweryInfoFragment.newInstance(brewery.id)
                    )
                    .addToBackStack(null)
                    .commit()
            }
        }
        binding.rvBreweries.adapter = adapter
        viewModel.breweriesList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {
        fun newInstance(): Fragment {
            return BreweriesFragment()
        }
    }
}