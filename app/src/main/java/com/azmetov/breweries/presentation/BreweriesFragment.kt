//package com.azmetov.breweries.presentation
//
//import android.util.Log
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//
//class BreweriesFragment : Fragment() {
//
//
//    val viewModel by lazy {
//        ViewModelProvider(this)[BreweriesViewModel::class.java]
//    }
//    private val binding by lazy {
//        ActivityBreweriesBinding.inflate(layoutInflater)
//    }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        val adapter = BreweryAdapter(this)
//        binding.rvBreweries.adapter = adapter
//        viewModel.breweriesList.observe(this) {
//            adapter.submitList(it)
//            log(it.toString())
//
//        }
//
//    }
//
//    private fun log(string: String) {
//        Log.d(TAG, string)
//    }
//
//    companion object {
//        private const val TAG = "BreweriesActivity_TAG"
//
//        fun newInstance
//
//
//    }
//}
package com.azmetov.breweries.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.azmetov.breweries.databinding.FragmentBreweriesBinding
import com.azmetov.breweries.presentation.adapters.BreweryAdapter

class BreweriesFragment : Fragment() {
    private var _binding: FragmentBreweriesBinding? = null
    private val binding: FragmentBreweriesBinding
        get() = _binding ?: throw RuntimeException("FragmentBreweriesBinding is null")

    val viewModel by lazy {
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
        binding.rvBreweries.adapter = adapter
        viewModel.breweriesList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            log(it.toString())
        }
    }


    private fun log(string: String) {
        Log.d(TAG, string)
    }

    companion object {
        private const val TAG = "BreweriesActivity_TAG"

        fun newInstance(): Fragment {
            return BreweriesFragment()
        }
    }
}