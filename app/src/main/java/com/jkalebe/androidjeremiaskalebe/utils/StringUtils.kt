package com.jkalebe.androidjeremiaskalebe.utils

import com.google.gson.Gson
import com.jkalebe.androidjeremiaskalebe.domain.models.Cliente
import com.jkalebe.androidjeremiaskalebe.domain.models.Pedido

fun String.toPedido(): Pedido = Gson().fromJson(this, Pedido::class.java)
fun String.toCliente(): Cliente = Gson().fromJson(this, Cliente::class.java)