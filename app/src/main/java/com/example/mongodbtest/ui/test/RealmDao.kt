package com.example.mongodbtest.ui.test

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RealmDao @Inject constructor(
    private val realm: Realm
) {

    fun insert(data: Information) = realm.writeBlocking {
        val consumer = ConsumerRealm().apply {
            name = data.name
            fatherName = data.fatherName
            nrc = data.nrc
        }
        val proposal = ProposalRealm().apply {
            paymentType = data.paymentType
            currencyType = data.currencyType
        }

        consumer.proposal = proposal

        copyToRealm(consumer)
    }

    fun updateInfo(data: Information){
        realm.writeBlocking {
            val consumer = realm.query<ConsumerRealm>("nrc == $0", data.nrc).find().first() ?: return@writeBlocking

            findLatest(consumer)?.let {
                it.proposal?.paymentType = data.paymentType
                it.proposal?.currencyType = data.currencyType
            }

        }
    }

    fun getConsumer(nrc: String) = realm
        .query<ConsumerRealm>("nrc == $0", nrc)
        .find()

    fun deleteAll(){
        realm.writeBlocking {
            deleteAll()
        }
    }

    fun getAllForm() = realm
        .query<ConsumerRealm>()
        .find()



}