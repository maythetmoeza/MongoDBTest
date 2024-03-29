package com.example.mongodbtest

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmAny
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ItemDao @Inject constructor(
    private val realm: Realm,
) {
    suspend fun insertItem(item: Item) = realm.writeBlocking { copyToRealm(item) }

    fun insertTownList(list: List<Township>) = realm.writeBlocking {
        val townList = TownList().apply {
            data.apply {
                list.forEach {
                    add(TownshipRealm().apply {
                        id = it.id
                        name = it.name
                        description = it.description
                    })
                }

            }
        }
        // Copy the object to the realm to return a managed instance
        copyToRealm(townList)
    }

    fun getAllTownship() : Flow<List<TownshipRealm>> = realm.query<TownshipRealm>().asFlow().map { it.list }

    fun getTownData()  = realm.query<TownshipRealm>().find()
    fun deleteTownship() {
        realm.writeBlocking {
            val realmResult = realm.query<TownshipRealm>().find()
            for (consumerRealm in realmResult) {
                findLatest(consumerRealm)?.let {
                    delete(it)
                }
            }
        }
    }
    fun getAllItems() : Flow<List<Item>> = realm.query<Item>().asFlow().map { it.list }

    suspend fun deleteAllItems() = realm.write {
        deleteAll()
    }

    suspend fun updateItem(item: Item) = realm.write {
        val findItem = query<Item>("owner_id == $0", item.owner_id).find()
        findLatest(findItem[0])?.isComplete = item.isComplete
        findLatest(findItem[0])?.summary = item.summary
    }

    suspend fun deleteItem(id: String) = realm.write {
        val findItem = query<Item>("owner_id == $0", id).find()
        delete(findItem)
    }

    fun getItem(id: String) = realm.query<Item>("id == $0", id).find()

    fun filterItem(isComplete: Boolean) = realm.query<Item>("isComplete == $0", isComplete).find().asFlow().map { it.list }

}