package com.sandymist.hellocompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandymist.hellocompose.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NamesViewModel: ViewModel() {
    private var addCounter = 0
    private val _names = MutableStateFlow(emptyList<String>())
    val names = _names.asStateFlow()
    private var namesData = MutableList(20) {
        val index = it + 1
        "Item $index"
    }

    private var data = MutableList(10) { index -> Item(index) }
    private val _listItems = MutableStateFlow(emptyList<Item>())
    val listItems = _listItems.asStateFlow()

    init {
        viewModelScope.launch {
            _listItems.emit(data)
        }
    }

    fun add(name: String) = viewModelScope.launch {
        namesData = namesData.toMutableList()
        namesData.add(0, "$name - ${++addCounter}")
        _names.emit(ArrayList(namesData))
    }

    suspend fun remove(item: Item) {
        data = data.toMutableList()
        data.remove(item)
        _listItems.emit(data)
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