package com.advanced.androidvitalsdemo.ui.slowrender.bindingnoconstraint

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.activityViewModels
import com.advanced.androidvitalsdemo.databinding.FragmentBindingNoConstraintBinding
import com.advanced.androidvitalsdemo.ui.slowrender.SlowRenderViewModel

class BindingNoConstraintFragment : Fragment() {

    private lateinit var binding: FragmentBindingNoConstraintBinding
    private val viewModel: SlowRenderViewModel by activityViewModels()

    private var startTime: Long = 0L
    private var endTime: Long = 0L

    override fun onAttach(context: Context) {
        super.onAttach(context)
        startTime = System.currentTimeMillis()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBindingNoConstraintBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                // This method is called just before the view is about to be drawn
                endTime = System.currentTimeMillis()
                // Remove the listener to avoid unnecessary calls
                view.viewTreeObserver.removeOnPreDrawListener(this)
                // Now you have both attachTime and displayTime
                val drawTime = endTime - startTime
                viewModel.updateDefaultBindingTime(drawTime)
                Log.d("TimingFragment", "Time to display: $drawTime ms")
                return true
            }
        })
    }


}