package com.example.mongodbtest.ui.testTwo

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mongodbtest.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RealmViewModel @Inject constructor(
    private val realmDao: RealmDao
): ViewModel() {
    val allCustomer: MutableList<Info> = mutableListOf()
    var drivers = mutableStateOf(emptyList<Driver>())
    init {
        getAllCustomer()
    }


    fun getAllCustomer() {
        viewModelScope.launch {
            realmDao.getAllCustomer().collectLatest {
                allCustomer.clear()
                for(customer in it) {
                    allCustomer.add(Info(
                        name = customer.name,
                        nrc = customer.nrc,
                        proposal = Proposal(
                            paymentType = customer.proposal?.paymentType,
                            currencyType = customer.proposal?.currencyType
                        ),
                        driver = customer.driver?.map { driver ->
                            Driver(
                                driverName = driver.driverName,
                                driverNrc = driver.driverNrc
                            )
                        }
                    ))
                }
            }
        }
    }

    fun getDriverList(nrc: String) {
        viewModelScope.launch {
           val realmResult = realmDao.getDriverList(nrc).driver
            drivers.value = realmResult.map { driver ->
                Driver(
                    driverName = driver.driverName,
                    driverNrc = driver.driverNrc
                )
            }
            Log.d("getDriverList", "getDriverList: $drivers")
        }
    }

    fun insertOrUpdateCustomer(customer: Customer) {
        realmDao.insertOrUpdateCustomer(customer)
    }

    fun insertOrUpdateDriver(driver: Driver, nrc: String) {
        realmDao.insertOrUpdateDriver(driver, nrc)
    }

    fun updateProposal(proposal: Proposal, nrc: String) {
        realmDao.updateProposal(proposal, nrc)
    }

//    fun updateDriver(driver: Driver, nrc: String) {
//        realmDao.updateDriver(driver, nrc)
//    }
    fun deleteAllCustomer() {
        realmDao.deleteAllCustomer()
    }
}