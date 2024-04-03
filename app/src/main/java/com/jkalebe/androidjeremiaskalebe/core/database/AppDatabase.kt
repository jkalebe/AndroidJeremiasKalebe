package br.com.paygo.smart.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jkalebe.androidjeremiaskalebe.domain.models.database.ClientEntity
import com.jkalebe.androidjeremiaskalebe.domain.models.database.PedidoEntity

@Database(entities = [PedidoEntity::class, ClientEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun orderDAO(): OrderDAO
    abstract fun clientDAO(): ClientDAO

}