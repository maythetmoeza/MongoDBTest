package com.example.mongodbtest

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Item(): RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var isComplete: Boolean = false
    var summary: String = ""
    var owner_id: String = ""
    constructor(ownerId: String = "") : this() {
        owner_id = ownerId
    }
}

class TownList(): RealmObject {
    var data: RealmList<TownshipRealm> = realmListOf()
}

class TownshipRealm(): RealmObject{
    var id : String = ""
    var name : String = ""
    var description : String = ""
}


