package com.jkalebe.androidjeremiaskalebe.domain.models.database

import androidx.room.Embedded
import androidx.room.Relation

data class ClientWithContacts(
    @Embedded val client: ClientEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "clientId"
    )
    val contacts: List<ContactEntity>
)