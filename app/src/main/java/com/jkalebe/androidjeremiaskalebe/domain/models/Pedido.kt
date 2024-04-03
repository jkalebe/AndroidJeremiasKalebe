package com.jkalebe.androidjeremiaskalebe.domain.models

import com.jkalebe.androidjeremiaskalebe.domain.models.database.OrderEntity

data class Pedido(
    val numero_ped_Rca: Int,
    val numero_ped_erp: String,
    val codigoCliente: String,
    val NOMECLIENTE: String,
    val data: String,
    val status: String,
    val critica: String?,
    val tipo: String,
    val legendas: List<String>
)
data class PedidosContainer(
    val pedidos: List<Pedido>
)

fun Pedido.toOrderEntity(clientId: Int): OrderEntity {
    return OrderEntity(
        id = 0, // Assumindo autoGenerate = true para o ID
        clientId = clientId,
        numero_ped_Rca = this.numero_ped_Rca,
        numero_ped_erp = this.numero_ped_erp,
        codigoCliente = this.codigoCliente,
        NOMECLIENTE = this.NOMECLIENTE,
        data = this.data,
        status = this.status,
        critica = this.critica,
        tipo = this.tipo,
        legendas = this.legendas
    )
}

