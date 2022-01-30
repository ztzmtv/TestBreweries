package com.azmetov.breweries.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.azmetov.breweries.R
import com.azmetov.breweries.databinding.FragmentBreweryInfoBinding

class BreweryInfoFragment : Fragment() {
    private var _binding: FragmentBreweryInfoBinding? = null
    private val binding: FragmentBreweryInfoBinding
        get() = _binding ?: throw RuntimeException("BreweryInfoFragment is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[BreweriesViewModel::class.java]
    }

    private var id: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreweryInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (id == null) id = savedInstanceState?.getString(EXTRA_ID)
        log("onCreate $id")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val extraId =
            requireArguments().getString(EXTRA_ID) ?: throw RuntimeException("id is null")

        id = extraId
        log("onViewCreated $extraId")
        viewModel.getBreweryInfo(extraId)
        viewModel.breweryItem.observe(viewLifecycleOwner) {
            with(binding) {
                setBreweryInfoText(
                    R.string.brewery_info_name_template,
                    tvBreweryInfoName,
                    it.name
                )
                setBreweryInfoText(
                    R.string.brewery_info_city_template,
                    tvBreweryInfoCity,
                    it.city
                )
                setBreweryInfoText(
                    R.string.brewery_info_phone_template,
                    tvBreweryInfoPhone,
                    it.phone
                )
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(EXTRA_ID, id)
        log("onSaveInstanceState $id")
    }

    private fun setBreweryInfoText(templateId: Int, textView: TextView, item: String?) {
        val template = requireContext().resources.getString(templateId)
        textView.text = String.format(template, item ?: SYMBOL_IF_EMPTY)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun log(string: String) {
        Log.d(TAG, string)
    }

    companion object {
        private const val TAG = "BreweryInfoFragment_TAG"
        private const val EXTRA_ID = "id"
        private const val SYMBOL_IF_EMPTY = "-"

        fun newInstance(id: String): Fragment {
            return BreweryInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_ID, id)
                }
            }
        }
    }
}