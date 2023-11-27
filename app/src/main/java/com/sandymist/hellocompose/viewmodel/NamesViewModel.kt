package com.sandymist.hellocompose.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NamesViewModel: ViewModel() {
    private var addCounter = 0
    private val _names = MutableStateFlow(emptyList<String>())
    val names = _names.asStateFlow()
    private var data = MutableList(20) {
        val index = it + 1
        "Item $index"
    }

    init {
        viewModelScope.launch {
            _names.emit(data)
        }
    }

    fun add(name: String) = viewModelScope.launch {
        Log.e(TAG, "+++++ ADD: $name")
        data = data.toMutableList()
        data.add(0, "$name - ${++addCounter}")
        _names.emit(ArrayList(data))
    }

    companion object {
        private const val TAG = "NamesViewModel"
    }
}

/*
class NamesViewModel: ViewModel() {
    private var addCounter = 0
    private val _names = MutableStateFlow<List<String>>(listOf())
    val names = _names.asStateFlow()
    private var data = MutableList(20) {
        val index = it + 1
        "Item $index"
    }

    init {
        viewModelScope.launch {
            _names.value = data.toMutableList()
        }
    }

    fun add(name: String) {
        Log.e(TAG, "+++++ ADD: $name")
        data.add(0, "$name - ${++addCounter}")
        _names.value = data.toMutableList()
    }

    companion object {
        private const val TAG = "NamesViewModel"
    }
}
*/