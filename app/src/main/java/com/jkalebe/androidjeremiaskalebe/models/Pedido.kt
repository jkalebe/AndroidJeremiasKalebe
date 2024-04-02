package com.jkalebe.androidjeremiaskalebe.models

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
