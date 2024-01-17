package com.example.mongodbtest

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemDao @Inject constructor(
    private val realm: Realm
) {
    suspend fun insertItem(item: Item) = realm.write {
        copyToRealm(item)
    }
    fun getAllItems() = realm.query<Item>().find().asFlow()
    suspend fun deleteAllItems() = realm.write {
        deleteAll()
    }
    suspend fun updateItem(ownerId: String) = realm.write {
        val findItem = query<Item>("owner_id == $0", ownerId).find()
        findLatest(findItem[0])?.isComplete = true
    }
    suspend fun deleteItem(id: String) = realm.write {
        val findItem = query<Item>("id == $0", id).find()
        delete(findItem)
    }
    fun getItem(id: String) = realm.query<Item>("id == $0", id).find()

    fun filterItem(name: String) = realm.query<Item>("name == $0", name).find().asFlow()

}