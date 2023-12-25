package com.advanced.androidvitalsdemo.ui.permission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PermissionViewModel : ViewModel() {
    private var _cameraPermission = MutableLiveData<Int>()
    val cameraPermisson: LiveData<Int>
        get() = _cameraPermission

    init {
        _cameraPermission.value = 0
    }

    fun updateCameraPermission(newData: Int) {
        _cameraPermission.value = newData
    }
}