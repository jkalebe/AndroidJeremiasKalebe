package com.jkalebe.androidjeremiaskalebe.domain.models.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jkalebe.androidjeremiaskalebe.domain.models.Pedido
import com.jkalebe.androidjeremiaskalebe.utils.toPedido

@Entity(tableName = "Pedidos")
data class PedidoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "data") val data: String,
)

fun PedidoEntity.toPedido(): Pedido {
    return this.data.toPedido()
}

fun LiveData<PedidoEntity?>.transformToPedido() : LiveData<Pedido?> {
    val pedidoTransformado = this.map {
        it?.toPedido()
    }
    return pedidoTransformado
}