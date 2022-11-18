package com.illia.finalproject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.illia.finalproject.R
import com.illia.finalproject.databinding.HomeFragmentBinding
import com.illia.finalproject.data.model.ForecastAdapter
import com.illia.finalproject.data.model.WeatherForecastDTO
import com.illia.finalproject.domain.MyViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null

    lateinit var valuesList: List<WeatherForecastDTO>

    lateinit var viewModel: MyViewModel



    private val adapter = ForecastAdapter { pos ->
        findNavController().navigate(
            HomeFragmentDirections
                .actionHomeFragmentToFullInfoFragment(valuesList[pos])
        )
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        val swipeRefreshLayout = binding.swipeRefreshLayout
        val recycleView = binding.recyclerView
        val searchButton = binding.searchButton

        recycleView.adapter = adapter

        searchButton.setOnClickListener {
            viewModel.viewModelScope.launch {
                val lat = binding.paramFieldLat.text.toString()
                val lon = binding.paramFieldLon.text.toString()
                val nod = binding.paramFieldNOD.text.toString()

                valuesList = viewModel.requestDataFromDb(lat, lon, nod)

                if(valuesList.isEmpty()){
                    adapter.clearValues()
                    binding.locationPlaceholder.setText(R.string.invalid_data)
                }else {
                    val location = valuesList.get(0).city ?: "Unknown"
                    binding.locationPlaceholder.setText(location.ifEmpty { "Unknown" })
                }
                adapter.setList(valuesList)
            }
        }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.viewModelScope.launch {
                val lat = binding.paramFieldLat.text.toString()
                val lon = binding.paramFieldLon.text.toString()
                val nod = binding.paramFieldNOD.text.toString()

                valuesList = viewModel.requestDataFromInternet(lat, lon, nod, false)

                if(valuesList.isEmpty()){
                    adapter.clearValues()
                    binding.locationPlaceholder.setText(R.string.invalid_data)
                }else {
                    val location = valuesList.get(0).city.toString()
                    binding.locationPlaceholder.setText(location.ifEmpty { "Unknown" })
                }
            }

            adapter.setList(valuesList)
            swipeRefreshLayout.isRefreshing = false
        }

        super.onViewCreated(view, savedInstanceState)
    }

}