package com.illia.finalproject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.illia.finalproject.databinding.HomeFragmentBinding
import com.illia.finalproject.model.ForecastAdapter
import com.illia.finalproject.model.WeatherForecastDTO
import java.util.*
import kotlin.random.Random

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null

//    lateinit var valuesList: List<WeatherForecastDTO>

    var items = listOf(
        WeatherForecastDTO(
            "for 2022-11-09 15:00:00\n",

            "cloudy",
            "https://i.pinimg.com/originals/ab/00/0f/ab000f469819dd7ef2a7e427e15305ff.jpg"
        ),
        WeatherForecastDTO(
            "for 2022-11-09 18:00:00\n",

            "snowy",
            "https://st.depositphotos.com/1297731/3381/i/450/depositphotos_33816641-stock-photo-christmas-background-with-snowy-firs.jpg"
        ),
        WeatherForecastDTO(
            "for 2022-11-09 21:00:00\n",

            "rainy",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8Wzj7v4JaP1S2YJ-lb51fPmtt4nR3YJQoNw&usqp=CAU"
        )
    )


    private val adapter = ForecastAdapter { pos ->
        findNavController().navigate(
            HomeFragmentDirections
                .actionHomeFragmentToFullInfoFragment(items[pos])
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



        println("home fragment created")
        val swipeRefreshLayout = binding.homeFragment
        val recycleView = binding.recyclerView

        recycleView.adapter = adapter

        adapter.setList(items)

        swipeRefreshLayout.setOnRefreshListener {
            items = items.reversed()
            adapter.setList(items)
            swipeRefreshLayout.isRefreshing = false
        }

        super.onViewCreated(view, savedInstanceState)
    }

}