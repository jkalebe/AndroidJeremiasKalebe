package com.jkalebe.androidjeremiaskalebe.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jkalebe.androidjeremiaskalebe.domain.models.database.OrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderEntity)

    @Query("SELECT * FROM Orders")
    fun getAllOrders(): Flow<List<OrderEntity>>

    @Query("SELECT * FROM Orders WHERE id = :orderId")
    fun getOrderById(orderId: Int): Flow<OrderEntity?>

    @Query("SELECT * FROM Orders WHERE clientId = :clientId")
    fun getOrderByClientId(clientId: Int): List<OrderEntity>?

    @Update
    suspend fun updateOrder(order: OrderEntity)
}