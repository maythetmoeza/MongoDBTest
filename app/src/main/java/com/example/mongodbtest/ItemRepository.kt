package com.example.mongodbtest

import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemRepository @Inject constructor(
    private val itemDao: ItemDao
) {
    val allItem : Flow<ResultsChange<Item>> = itemDao.getAllItems()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    suspend fun insertItem(item: Item) = coroutineScope.launch(Dispatchers.IO) {
        itemDao.insertItem(item)
    }
    suspend fun deleteAllItems() = coroutineScope.launch(Dispatchers.IO) {
        itemDao.deleteAllItems()
    }
    suspend fun deleteItem(id: String) = coroutineScope.launch(Dispatchers.IO) {
        itemDao.deleteItem(id)
    }
    fun getItem(id: String) = coroutineScope.launch(Dispatchers.IO) {
        itemDao.getItem(id)
    }

    fun filterItem(name: String) = coroutineScope.launch(Dispatchers.IO) {
        itemDao.filterItem(name)
    }
    suspend fun updateItem(ownerId: String) = coroutineScope.launch(Dispatchers.IO) {
        itemDao.updateItem(ownerId)
    }
}