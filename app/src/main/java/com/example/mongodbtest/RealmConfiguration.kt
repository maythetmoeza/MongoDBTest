package com.example.mongodbtest

import com.example.mongodbtest.ui.test.ConsumerRealm
import com.example.mongodbtest.ui.test.ProposalRealm
import com.example.mongodbtest.ui.testTwo.CustomerRealm
import com.example.mongodbtest.ui.testTwo.DriverRealm
import com.example.mongodbtest.ui.testTwo.PropoRealm
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
            .Builder(schema =
            setOf(
                Item::class,
                TownList::class,
                TownshipRealm::class,
                ConsumerRealm::class,
                ProposalRealm::class,
                CustomerRealm::class,
                PropoRealm::class,
                DriverRealm::class,

            )
            )
            .compactOnLaunch()
            .deleteRealmIfMigrationNeeded()
            .build()

        return Realm.open(config)
    }

    @Provides
    @Singleton
    fun providesItemDao(realm: Realm): ItemDao {
        return ItemDao(realm)
    }

}