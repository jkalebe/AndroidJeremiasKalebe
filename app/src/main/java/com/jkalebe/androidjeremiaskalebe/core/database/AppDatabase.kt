package com.jkalebe.androidjeremiaskalebe.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jkalebe.androidjeremiaskalebe.domain.models.database.ClientEntity
import com.jkalebe.androidjeremiaskalebe.domain.models.database.ContactEntity
import com.jkalebe.androidjeremiaskalebe.domain.models.database.OrderEntity
import com.jkalebe.androidjeremiaskalebe.utils.Converters

@Database(entities = [OrderEntity::class, ClientEntity::class, ContactEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun orderDAO(): OrderDAO
    abstract fun clientDAO(): ClientDAO
    abstract fun contactDAO(): ContactDAO

}