package br.com.paygo.smart.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jkalebe.androidjeremiaskalebe.core.database.ClientDAO
import com.jkalebe.androidjeremiaskalebe.core.database.ContactDAO
import com.jkalebe.androidjeremiaskalebe.core.database.OrderDAO
import com.jkalebe.androidjeremiaskalebe.domain.models.database.ClientEntity
import com.jkalebe.androidjeremiaskalebe.domain.models.database.ContactEntity
import com.jkalebe.androidjeremiaskalebe.domain.models.database.OrderEntity

@Database(entities = [OrderEntity::class, ClientEntity::class, ContactEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun orderDAO(): OrderDAO
    abstract fun clientDAO(): ClientDAO
    abstract fun contactDAO(): ContactDAO

}