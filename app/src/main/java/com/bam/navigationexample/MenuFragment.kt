package com.bam.navigationexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.bam.navigationexample.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {

    lateinit var binding: FragmentMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStress.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_menuFragment_to_firstFragment)
        }
        binding.buttonTonus.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_menuFragment_to_thirdFragment)
        }

    }
}