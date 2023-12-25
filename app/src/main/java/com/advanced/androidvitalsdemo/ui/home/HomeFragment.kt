package com.advanced.androidvitalsdemo.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.advanced.androidvitalsdemo.R
import com.advanced.androidvitalsdemo.databinding.FragmentHomeBinding
import com.advanced.androidvitalsdemo.MainViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val endTime = System.currentTimeMillis()
        mainViewModel.updateEndTime(endTime)
        Log.i("StartupTime", "onViewCreated: $endTime")
        mainViewModel.calculateStartupTime()
        mainViewModel.startupTime.observe(viewLifecycleOwner) {
            binding.textData.text = "${it}ms ~ ${convertMillisecondToSecond(it)}"
        }
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
        binding.btnPermission.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_permissionFragment)
        }
    }

    private fun convertMillisecondToSecond(milliseconds: Long): String {
        val seconds = milliseconds / 1000.0
        return String.format("%.3fs", seconds)
    }

}