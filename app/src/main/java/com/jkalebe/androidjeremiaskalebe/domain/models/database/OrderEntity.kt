package com.jkalebe.androidjeremiaskalebe.domain.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.jkalebe.androidjeremiaskalebe.domain.models.Pedido

@Entity(
    tableName = "Orders", foreignKeys = [ForeignKey(
        entity = ClientEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("clientId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "clientId") val clientId: Int,
    @ColumnInfo(name = "numeroPedRca") val numero_ped_Rca: Int,
    @ColumnInfo(name = "numeroPedErp") val numero_ped_erp: String,
    @ColumnInfo(name = "codigoCliente") val codigoCliente: String,
    @ColumnInfo(name = "NOMECLIENTE") val NOMECLIENTE: String,
    @ColumnInfo(name = "data") val data: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "critica") val critica: String?,
    @ColumnInfo(name = "tipo") val tipo: String,
    @ColumnInfo(name = "legendas") val legendas: List<String>
)

fun OrderEntity.toPedido(): Pedido {
    return Pedido(
        numero_ped_Rca,
        numero_ped_erp,
        codigoCliente,
        NOMECLIENTE,
        data,
        status,
        critica,
        tipo,
        legendas
    )
}
