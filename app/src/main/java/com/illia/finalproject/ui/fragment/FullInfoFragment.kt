package com.illia.finalproject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.illia.finalproject.R
import com.illia.finalproject.databinding.FullInfoFragmentBinding

class FullInfoFragment : Fragment(){

    private var _binding: FullInfoFragmentBinding? = null

    private val binding get() = _binding!!

    val args: FullInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FullInfoFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("full info fragment created")
        val argument = args.wfDTO

        binding.fullDescriptionTextView.setText(argument.timePeriod + " asd" + argument.overallState)
        Glide.with(view)
            .load(argument.image)
            .centerCrop()
            .placeholder(binding.weatherStateImagePlaceholder.drawable)
            .error(R.drawable.ic_launcher_foreground)
            .fallback(R.drawable.ic_launcher_foreground)
            .into(binding.weatherStateImagePlaceholder)

        binding.backImage.setOnClickListener{
            findNavController().navigate(FullInfoFragmentDirections.actionFullInfoFragmentToHomeFragment())
        }

        super.onViewCreated(view, savedInstanceState)
    }
}