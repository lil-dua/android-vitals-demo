package com.advanced.androidvitalsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val startTime = System.currentTimeMillis()
        mainViewModel.updateStartTime(startTime)
        Log.i("StartupTime", "onCreate: $startTime")
        setContentView(R.layout.activity_main)
    }
}