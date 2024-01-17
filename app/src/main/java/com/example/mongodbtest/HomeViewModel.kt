package com.example.mongodbtest

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val itemDao: ItemDao
) : ViewModel() {

    var allItem = mutableStateOf(emptyList<Item>())
    var ownerId = mutableStateOf("")
    var summary = mutableStateOf("")
    var isComplete = mutableStateOf("")
    fun getAllItem(){
        viewModelScope.launch {
            itemDao.getAllItems().collect {
                allItem.value = it
            }
        }
    }

    fun insertItem() = viewModelScope.launch(Dispatchers.IO) {
        itemDao.insertItem(Item().apply {
            summary = this@HomeViewModel.summary.value
            owner_id = this@HomeViewModel.ownerId.value
            isComplete = this@HomeViewModel.isComplete.value.toBoolean()
        })

    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        itemDao.deleteAllItems()
    }
    fun deleteItem() = viewModelScope.launch(Dispatchers.IO) {
        itemDao.deleteItem(ownerId.value)
    }

    fun updateItem() = viewModelScope.launch(Dispatchers.IO) {
        itemDao.updateItem(Item().apply {
            summary = this@HomeViewModel.summary.value
            owner_id = this@HomeViewModel.ownerId.value
            isComplete = this@HomeViewModel.isComplete.value.toBoolean()
        })
    }

    fun filterItem() = viewModelScope.launch {
        itemDao.filterItem(isComplete.value.toBoolean()).collect {
            allItem.value = it
        }
    }

}