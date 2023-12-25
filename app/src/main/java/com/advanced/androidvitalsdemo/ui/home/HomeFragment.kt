package com.advanced.androidvitalsdemo.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.advanced.androidvitalsdemo.R
import com.advanced.androidvitalsdemo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAnr.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_anrsFragment)
        }
        binding.btnCrash.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_crashesFragment)
        }
        binding.btnSlowRender.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_slowRenderFragment)
        }
        binding.btnWifi.setOnClickListener {

        }
    }
}