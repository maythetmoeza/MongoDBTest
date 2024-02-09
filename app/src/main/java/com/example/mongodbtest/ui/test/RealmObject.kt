package com.example.mongodbtest.ui.test

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class ConsumerRealm(): RealmObject{
    var name: String? = null
    var fatherName: String? = null
    var nrc: String? = null
}

class ProposalRealm(): RealmObject{
    var paymentType: String? = null
    var currencyType: String? = null
}

class FormRealm(): RealmObject{
    var consumer: ConsumerRealm? = null
    var proposal: ProposalRealm? = null
}
class FormRealmList(): RealmObject{
    var data: RealmList<FormRealm> = realmListOf()
}

data class FormList(
    var data: RealmList<FormRealm> = realmListOf()
)

data class Consumer(
    val name: String? = null,
    val fatherName: String? = null,
    val nrc: String? = null
)

data class Proposal(
    val paymentType: String? = null,
    val currencyType: String? = null
)

data class Form(
    val consumer: Consumer? = null,
    val proposal: Proposal? = null
)