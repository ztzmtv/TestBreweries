package com.azmetov.breweries.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.azmetov.breweries.R
import com.azmetov.breweries.databinding.FragmentBreweryInfoBinding

class BreweryInfoFragment : Fragment() {
    private var _binding: FragmentBreweryInfoBinding? = null
    private val binding: FragmentBreweryInfoBinding
        get() = _binding ?: throw RuntimeException("BreweryInfoFragment is null")

    val viewModel by lazy {
        ViewModelProvider(this)[BreweriesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreweryInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireArguments().getString(EXTRA_ID) ?: throw RuntimeException("id is null")
        viewModel.getBreweryInfo(id)
        viewModel.breweryItem.observe(viewLifecycleOwner) {
            with(binding) {
                val breweryNameTemplate =
                    requireContext().resources.getString(R.string.brewery_info_name_template)
                tvBreweryInfoName.text = String.format(breweryNameTemplate, it.name)
                val breweryCityTemplate =
                    requireContext().resources.getString(R.string.brewery_info_city_template)
                tvBreweryInfoCity.text = String.format(breweryCityTemplate, it.city)
                val breweryPhoneTemplate =
                    requireContext().resources.getString(R.string.brewery_info_phone_template)
                tvBreweryInfoPhone.text = String.format(breweryPhoneTemplate, it.phone)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val EXTRA_ID = "id"

        fun newInstance(id: String): Fragment {
            return BreweryInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_ID, id)
                }
            }
        }
    }
}