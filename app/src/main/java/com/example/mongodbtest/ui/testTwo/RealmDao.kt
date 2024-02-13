package com.example.mongodbtest.ui.testTwo

import android.util.Log
import com.example.mongodbtest.ui.test.ConsumerRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.query.find
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RealmDao @Inject constructor(
    private val realm: Realm
) {
    fun insertOrUpdateCustomer(customer: Customer){
        realm.writeBlocking {
            val realmResult = realm.query<CustomerRealm>("nrc == $0", customer.nrc).find().firstOrNull()

            if (realmResult != null) {
                findLatest(realmResult)?.let {
                    it.name = customer.name
                    it.nrc = customer.nrc
                }
            } else {
                val consumer = CustomerRealm().apply {
                    name = customer.name
                    nrc = customer.nrc
                    proposal = PropoRealm().apply {
                        paymentType = null
                        currencyType = null
                    }
                }
                copyToRealm(consumer)
            }

        }
    }

    fun updateProposal(proposal: Proposal, nrc: String) {
        realm.writeBlocking {
            val consumer = realm.query<CustomerRealm>("nrc == $0", nrc).find().first() ?: return@writeBlocking

            findLatest(consumer)?.let {
                it.proposal?.paymentType = proposal.paymentType
                it.proposal?.currencyType = proposal.currencyType
            }
        }
    }

//    fun updateDriver(driver: Driver, nrc: String) {
//        realm.writeBlocking {
//            val consumer = realm.query<CustomerRealm>("nrc == $0", nrc).find().first() ?: return@writeBlocking
//            findLatest(consumer)?.let {
//                it.driver?.add(DriverRealm().apply {
//                    driverName = driver.driverName
//                    driverNrc = driver.driverNrc
//                })
//            }
//        }
//    }

    fun insertOrUpdateDriver(driver: Driver, nrc: String) {
        realm.writeBlocking {
            val consumer = realm.query<CustomerRealm>("nrc == $0", nrc).find().first() ?: return@writeBlocking

            findLatest(consumer)?.let {
                it.driver?.find { driverRealm -> driverRealm.driverNrc == driver.driverNrc }
                    ?.let { driverResult ->
                        driverResult.driverName = driver.driverName
                    } ?: it.driver?.add(DriverRealm().apply {
                    driverName = driver.driverName
                    driverNrc = driver.driverNrc
                })
            }

        }

    }

    fun getAllCustomer(): Flow<List<CustomerRealm>> = realm.query<CustomerRealm>().asFlow().map { it.list }

    fun getDriverList(nrc: String) : CustomerRealm {
        return realm.query<CustomerRealm>("nrc == $0", nrc).find().first() ?: return CustomerRealm()
    }

    fun deleteAllCustomer() {
        realm.writeBlocking {
            deleteAll()
        }
    }


}