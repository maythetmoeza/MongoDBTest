package com.example.mongodbtest.ui.test

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class ConsumerRealm: RealmObject{
    @PrimaryKey var _id: ObjectId = ObjectId()
    var name: String? = null
    var fatherName: String? = null
    var nrc: String? = null
    var proposal: ProposalRealm? = null
}

class ProposalRealm: EmbeddedRealmObject{
    var paymentType: String? = null
    var currencyType: String? = null
}

data class Information(
    val name: String,
    val fatherName: String,
    val nrc: String,
    val paymentType: String? = null,
    val currencyType: String? =null
)