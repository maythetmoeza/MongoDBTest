package com.example.mongodbtest

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : ViewModel(){

    val allItem = itemRepository.allItem

    suspend fun insertItem(item: Item){
        itemRepository.insertItem(item)
    }
    suspend fun deleteAllItems(){
        itemRepository.deleteAllItems()
    }
    suspend fun deleteItem(id: String){
        itemRepository.deleteItem(id)
    }
    fun getItem(id: String){
        itemRepository.getItem(id)
    }
    fun filterItem(name: String){
        itemRepository.filterItem(name)
    }
    suspend fun updateItem(ownerId: String){
        itemRepository.updateItem(ownerId)
    }
}