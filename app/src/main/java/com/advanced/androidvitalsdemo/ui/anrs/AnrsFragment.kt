package com.advanced.androidvitalsdemo.ui.anrs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.advanced.androidvitalsdemo.databinding.FragmentAnrsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnrsFragment : Fragment() {
    private lateinit var binding: FragmentAnrsBinding
    private var data: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnrsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClear.setOnClickListener {
            data = 0
            binding.textData.text = data.toString()
        }

        binding.btnImportDataMain.setOnClickListener {
            performLongRunningTask()
            binding.textData.text = data.toString()
        }

        binding.btnImportData.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                withContext(Dispatchers.Default) {
                    // Công việc tốn thời gian chạy trên luồng nền
                    performLongRunningTask()
                }

                // Update UI sau khi công việc hoàn thành
                binding.textData.text = data.toString()
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun performLongRunningTask() {
        // Một vòng lặp lớn mô phỏng công việc tốn thời gian
        for (i in 0 until 500000) {
            // Công việc giả lập tốn thời gian
            doSomethingTimeConsuming()
            data = i
            Log.i("AnrsFragment", "performLongRunningTask: $i")
        }
    }

    private fun doSomethingTimeConsuming() {
        // Giả lập một công việc tốn thời gian
        for (i in 0 until 1000) {
            for (j in 0 until 1000) {
                // Công việc giả lập tốn thời gian
            }
        }
    }
}