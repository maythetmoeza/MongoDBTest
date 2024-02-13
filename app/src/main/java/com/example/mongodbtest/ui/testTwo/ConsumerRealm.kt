package com.example.mongodbtest.ui.testTwo

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class CustomerRealm: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var name: String? = null
    var nrc: String? = null
    var proposal: PropoRealm? = null
    var driver: RealmList<DriverRealm> = realmListOf()
}

class PropoRealm: RealmObject {
    var paymentType: String? = null
    var currencyType: String? = null
}

class DriverRealm: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var driverName: String? = null
    var driverNrc: String? = null
}
