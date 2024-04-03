package com.jkalebe.androidjeremiaskalebe.domain.models.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jkalebe.androidjeremiaskalebe.domain.models.Cliente
import com.jkalebe.androidjeremiaskalebe.utils.toCliente

@Entity(tableName = "Client")
data class ClientEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "data") val data: String,
)

fun ClientEntity.toCliente(): Cliente {
    return this.data.toCliente()
}

fun LiveData<ClientEntity?>.transformToPedido() : LiveData<Cliente?> {
    val pedidoTransformado = this.map {
        it?.toCliente()
    }
    return pedidoTransformado
}