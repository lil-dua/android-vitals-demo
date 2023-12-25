package com.advanced.androidvitalsdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/***
 * Created by HoangRyan aka LilDua on 12/25/2023.
 */
class MainViewModel: ViewModel() {
    private var _startupTime = MutableLiveData<Long>()
    val startupTime: LiveData<Long>
        get() = _startupTime

    private var _startTime = MutableLiveData<Long>()
    private var _endTime = MutableLiveData<Long>()


    init {
        _startupTime.value = 0L
    }

    fun updateStartTime(newData: Long) {
        _startTime.value = newData
    }

    fun updateEndTime(newData: Long) {
        _endTime.value = newData
    }

    fun calculateStartupTime() {
        _startupTime.value = _endTime.value!! - _startTime.value!!
    }
}