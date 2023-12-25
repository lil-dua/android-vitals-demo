package com.advanced.androidvitalsdemo.ui.slowrender.databinding

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.advanced.androidvitalsdemo.databinding.FragmentDataBindingBinding

class DataBindingFragment : Fragment() {

    private lateinit var binding: FragmentDataBindingBinding
    private var startTime: Long = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        startTime = SystemClock.elapsedRealtime()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataBindingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Ghi lại thời điểm kết thúc sau khi Fragment đã được khởi tạo
        val endTime = SystemClock.elapsedRealtime()

        // Tính toán thời gian khởi tạo và hiển thị nó
        val initializationTime = endTime - startTime
        binding.textTime.text = "Render Time: $initializationTime ms \n ${initializationTime/1000} second"
    }

}