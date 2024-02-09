package com.example.mongodbtest.ui.test

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import javax.inject.Inject

class RealmDao @Inject constructor(
    private val realm: Realm
) {

    fun insertFormData(form: Form) = realm.writeBlocking {
        val formRealm = FormRealmList().apply {
            data.apply {
                add(
                FormRealm().apply {
                    consumer = ConsumerRealm().apply {
                        name = form.consumer?.name
                        fatherName = form.consumer?.fatherName
                        nrc = form.consumer?.nrc
                    }
                    proposal = ProposalRealm().apply {
                        paymentType = form.proposal?.paymentType
                        currencyType = form.proposal?.currencyType
                    }
                }
            )
            }
            }

        copyToRealm(formRealm)
    }

    fun getAllForm() = realm.query<FormRealm>().find()

    fun getLastForm(): Form? {
        val formRealm = realm.query<FormRealm>().find().last()
        return Form(
            consumer = Consumer(
                name = formRealm.consumer?.name,
                fatherName = formRealm.consumer?.fatherName,
                nrc = formRealm.consumer?.nrc
            ),
            proposal = Proposal(
                paymentType = formRealm.proposal?.paymentType,
                currencyType = formRealm.proposal?.currencyType
            )
        )
    }

    fun updateForm(form: Form) = realm.writeBlocking {
        val formRealm = realm.query<FormRealm>().find().last()
        val updateRealm = FormRealm().apply {
            consumer = ConsumerRealm().apply {
                name = form.consumer?.name
                fatherName = form.consumer?.fatherName
                nrc = form.consumer?.nrc
            }
            proposal = ProposalRealm().apply {
                paymentType = form.proposal?.paymentType
                currencyType = form.proposal?.currencyType
            }
        }
        findLatest(formRealm)?.let {
            it.consumer = updateRealm.consumer
            it.proposal = updateRealm.proposal
        }

        val consumerRealm = realm.query<ConsumerRealm>().find().last()
        findLatest(consumerRealm)?.let {
            it.name = form.consumer?.name
            it.fatherName = form.consumer?.fatherName
            it.nrc = form.consumer?.nrc
        }


        val proposalRealm = realm.query<ProposalRealm>().find().last()
        findLatest(proposalRealm)?.let {
            it.paymentType = form.proposal?.paymentType
            it.currencyType = form.proposal?.currencyType
        }



    }

    fun deleteAll() = realm.writeBlocking {
        deleteAll()
    }
}