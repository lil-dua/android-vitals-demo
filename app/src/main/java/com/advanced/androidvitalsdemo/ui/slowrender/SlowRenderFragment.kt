package com.advanced.androidvitalsdemo.ui.slowrender

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.advanced.androidvitalsdemo.R
import com.advanced.androidvitalsdemo.databinding.FragmentSlowRenderBinding

class SlowRenderFragment : Fragment() {
    private lateinit var binding: FragmentSlowRenderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSlowRenderBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnDataBinding.setOnClickListener {
            findNavController().navigate(R.id.action_slowRenderFragment_to_dataBindingFragment)
        }
    }

}