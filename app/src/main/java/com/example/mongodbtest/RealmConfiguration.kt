package com.example.mongodbtest

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RealmConfiguration {
    @Provides
    @Singleton
    fun providesRealmConfigs(): Realm {
        val config = RealmConfiguration
            .Builder(schema = setOf(Item::class, TownList::class, TownshipRealm::class)).compactOnLaunch()
            .build()

        return Realm.open(config)
    }

    @Provides
    @Singleton
    fun providesItemDao(realm: Realm): ItemDao {
        return ItemDao(realm)
    }

}