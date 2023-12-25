package com.advanced.androidvitalsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.metrics.performance.JankStats
import androidx.metrics.performance.PerformanceMetricsState

class MainActivity : AppCompatActivity() {
    private lateinit var jankStats: JankStats

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // metrics state holder can be retrieved regardless of JankStats initialization
        val metricsStateHolder = PerformanceMetricsState
            .getHolderForHierarchy(layoutInflater.inflate(R.layout.activity_main,null))

        // initialize JankStats for current window
        jankStats = JankStats.createAndTrack(window, jankFrameListener)

        // add activity name as state
        metricsStateHolder.state?.putState("Activity", javaClass.simpleName)
    }

    override fun onResume() {
        super.onResume()
        jankStats.isTrackingEnabled = true
    }

    override fun onPause() {
        super.onPause()
        jankStats.isTrackingEnabled = false
    }

    private val jankFrameListener = JankStats.OnFrameListener { frameData ->
        // A real app could do something more interesting, like writing the info to local storage and later on report it.
        Log.v("JankStatsSample", frameData.toString())
    }
}