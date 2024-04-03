package com.jkalebe.androidjeremiaskalebe.domain.models.database

import androidx.room.Embedded
import androidx.room.Relation

data class ClientWithOrders(
    @Embedded val client: ClientEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "clientId"
    )
    val orders: List<OrderEntity>
)