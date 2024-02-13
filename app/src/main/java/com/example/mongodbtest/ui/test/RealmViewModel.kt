package com.example.mongodbtest.ui.test

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RealmViewModel @Inject constructor(
    private val realmDao: RealmDao
): ViewModel() {

    val allForm: MutableList<ConsumerRealm> = mutableListOf()
    var alreadyExist = mutableStateOf(false)
    var consumer: ConsumerRealm? = null

    init {
        getAllForm()
    }
    fun insert(data: Information) = viewModelScope.launch {
        realmDao.insert(data)
    }
    fun updateInfo(data: Information) = viewModelScope.launch {
        realmDao.updateInfo(data)
    }
    fun deleteAll() = viewModelScope.launch {
        realmDao.deleteAll()
    }

    fun getAllForm(){
        viewModelScope.launch {
            val realmResult = realmDao.getAllForm()
            for(result in realmResult){
                allForm.add(result)
            }
        }
    }

    fun getConsumer(nrc: String) {
        viewModelScope.launch {
            val realmResult = realmDao.getConsumer(nrc)
            if(realmResult.isNotEmpty()){
                consumer = realmResult.first()
                alreadyExist.value = true
            }
        }
    }
}