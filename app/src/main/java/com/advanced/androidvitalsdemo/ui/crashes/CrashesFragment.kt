package com.advanced.androidvitalsdemo.ui.crashes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.advanced.androidvitalsdemo.databinding.FragmentCrashesBinding
import java.net.HttpURLConnection
import java.net.URL

class CrashesFragment : Fragment() {

    private lateinit var binding: FragmentCrashesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCrashesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnNullPointer.setOnClickListener {
            nullPointerDemo()
        }

        binding.btnIndexOut.setOnClickListener {
            indexOutOfBoundDemo()
        }

        binding.btnOutMemory.setOnClickListener {
            outOfMemoryDemo()
        }
        binding.btnNetwork.setOnClickListener {
            networkOnMainThreadDemo()
        }
        binding.btnRuntime.setOnClickListener {
            androidRuntimeDemo()
        }
    }

    private fun nullPointerDemo() {
        var nullableString: String? = null
        // Gây ra NullPointerException khi cố gắng truy cập thuộc tính hoặc phương thức trên nullableString
        val length = nullableString!!.length

        //solution 1: check null
//        val length = if (nullableString != null) nullableString.length else 0

        //solution 2:
//        val length = nullableString?.length ?: 0
        binding.textData.text = length.toString()
    }

    private fun indexOutOfBoundDemo() {
        val numbers = listOf(1, 2, 3, 4, 5)
        // Gây ra IndexOutOfBoundsException vì chỉ số 10 nằm ngoài phạm vi của mảng
        val element = numbers[10]

        //solution 1: dùng try-catch
//        try {
//            val element = numbers[10]
//            binding.textData.text = element.toString()
//        } catch (e: IndexOutOfBoundsException) {
//            println("Caught an IndexOutOfBoundsException: ${e.message}")
//        }

        //solution 2: sử dụng hàm getOrNull
//        val element = numbers.getOrNull(10)
//        if (element != null) {
//            binding.textData.text = element.toString()
//        } else {
//            binding.textData.text = "Index out of bounds"
//        }

    }

    private fun outOfMemoryDemo() {
        val list = mutableListOf<Byte>()

//        try {
        while (true) {
            // Mỗi lần lặp, thêm một lượng dữ liệu mới vào danh sách
            val byteArray = ByteArray(10 * 1024 * 1024) // 10 MB
            list.addAll(byteArray.asList())
        }
//        } catch (e: OutOfMemoryError) {
//            println("Caught an OutOfMemoryError: ${e.message}")
//        }
    }

    private fun networkOnMainThreadDemo() {
        // Gửi yêu cầu HTTP, ví dụ sử dụng HttpURLConnection
        val url = URL("https://example.com/api/data")
        val connection = url.openConnection() as HttpURLConnection
        try {
            val inputStream = connection.inputStream
            return inputStream.bufferedReader().use { it.readText() }
        } finally {
            connection.disconnect()
        }
    }

    private fun androidRuntimeDemo() {
        // Gây ra một lỗi chạy thời gian bằng cách chia một số cho 0
        val result = 10.div(0)
        println("Result: $result")
    }


}