package com.jkalebe.androidjeremiaskalebe.data.remote

import com.jkalebe.androidjeremiaskalebe.domain.models.Cliente
import com.jkalebe.androidjeremiaskalebe.domain.models.DataRaw
import com.jkalebe.androidjeremiaskalebe.domain.models.Pedido
import com.jkalebe.androidjeremiaskalebe.domain.models.PedidosContainer
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("android/pedido")
    suspend fun getOrders(
    ): Response<PedidosContainer>

    @GET("android/cliente")
    suspend fun getClient(
    ): Response<DataRaw>
}