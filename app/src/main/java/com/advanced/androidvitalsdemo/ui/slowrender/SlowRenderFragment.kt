package com.advanced.androidvitalsdemo.ui.slowrender

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.advanced.androidvitalsdemo.R
import com.advanced.androidvitalsdemo.databinding.FragmentSlowRenderBinding

class SlowRenderFragment : Fragment() {

    private lateinit var binding: FragmentSlowRenderBinding
    private val viewModel: SlowRenderViewModel by activityViewModels()
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

        viewModel.defaultTime.observe(viewLifecycleOwner) {
            binding.textDataNotConstraint.text = "${it}ms"
        }

        viewModel.defaultBindingTime.observe(viewLifecycleOwner) {
            binding.textDataBindingNotConstraint.text = "${it}ms"
        }

        viewModel.constraintTime.observe(viewLifecycleOwner) {
            binding.textDataConstraint.text = "${it}ms"
        }

        viewModel.constraintBindingTime.observe(viewLifecycleOwner) {
            binding.textDataBindingConstraint.text = "${it}ms"
        }

        binding.btnDefaultRender.setOnClickListener {
            findNavController().navigate(R.id.action_slowRenderFragment_to_noConstraintFragment)
        }
        binding.btnDataBindingRender.setOnClickListener {
            findNavController().navigate(R.id.action_slowRenderFragment_to_bindingNoConstraintFragment)
        }
        binding.btnUseConstraint.setOnClickListener {
            findNavController().navigate(R.id.action_slowRenderFragment_to_useConstraintFragment)
        }
        binding.btnDataBindingConstraint.setOnClickListener {
            findNavController().navigate(R.id.action_slowRenderFragment_to_bindingConstraintFragment)
        }
    }
}