package com.advanced.androidvitalsdemo.ui.slowrender

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SlowRenderViewModel : ViewModel() {
    private var _defaultTime = MutableLiveData<Long>()
    val defaultTime: LiveData<Long>
        get() = _defaultTime

    private var _defaultBindingTime = MutableLiveData<Long>()
    val defaultBindingTime: LiveData<Long>
        get() = _defaultBindingTime

    private var _constraintTime = MutableLiveData<Long>()
    val constraintTime: LiveData<Long>
        get() = _constraintTime

    private var _constraintBindingTime = MutableLiveData<Long>()
    val constraintBindingTime: LiveData<Long>
        get() = _constraintBindingTime

    init {
        _defaultTime.value = 0L
        _defaultBindingTime.value = 0L
        _constraintTime.value = 0L
        _constraintBindingTime.value = 0L
    }

    fun updateDefaultTime(newData: Long) {
        _defaultTime.value = newData
    }

    fun updateDefaultBindingTime(newData: Long) {
        _defaultBindingTime.value = newData
    }

    fun updateConstraintTime(newData: Long) {
        _constraintTime.value = newData
    }

    fun updateConstraintBindingTime(newData: Long) {
        _constraintBindingTime.value = newData
    }
}