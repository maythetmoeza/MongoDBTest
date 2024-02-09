package com.example.mongodbtest.ui.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RealmViewModel @Inject constructor(
    private val realmDao: RealmDao
): ViewModel() {
    var lastForm: Form? = null
    var allForm:MutableList<Form> = mutableListOf()

    init {
        getAllForm()
    }
    fun insertFormData(form: Form){
        viewModelScope.launch {
            realmDao.insertFormData(form)
        }
    }
    private fun getAllForm() {
        viewModelScope.launch {
            val realmResult = realmDao.getAllForm()
            for(form in realmResult){
                allForm.add(
                    Form(
                        consumer = Consumer(
                            name = form.consumer?.name,
                            fatherName = form.consumer?.fatherName,
                            nrc = form.consumer?.nrc
                        ),
                        proposal = Proposal(
                            paymentType = form.proposal?.paymentType,
                            currencyType = form.proposal?.currencyType
                        )
                    )
                )

            }
        }
    }
    fun getLastForm() {
        viewModelScope.launch {
            lastForm = realmDao.getLastForm()
        }
    }
    fun updateForm(form: Form) = realmDao.updateForm(form)

    fun deleteAllForm() = realmDao.deleteAll()
}