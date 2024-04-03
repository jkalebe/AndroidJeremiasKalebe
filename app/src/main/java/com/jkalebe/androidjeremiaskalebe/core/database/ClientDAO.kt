package com.jkalebe.androidjeremiaskalebe.core.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.jkalebe.androidjeremiaskalebe.domain.models.database.ClientEntity
import com.jkalebe.androidjeremiaskalebe.domain.models.database.ClientWithContacts
import com.jkalebe.androidjeremiaskalebe.domain.models.database.ClientWithOrders
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClient(pedido: ClientEntity)

    @Query("SELECT * FROM Client")
    fun getAllClients(): Flow<List<ClientEntity>>

    @Query("SELECT * FROM Client WHERE id = :clientId")
    fun getClientById(clientId: Int): Flow<ClientEntity?>

    @Update
    suspend fun updateClient(client: ClientEntity)

    @Transaction
    @Query("SELECT * FROM Client WHERE id = :clientId")
    fun getClientWithContacts(clientId: Int): ClientWithContacts?

    @Transaction
    @Query("SELECT * FROM Client WHERE id = :clientId")
    fun getClientWithOrders(clientId: Int): LiveData<ClientWithOrders>
}