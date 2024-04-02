package com.jkalebe.androidjeremiaskalebe.data.repository

import com.jkalebe.androidjeremiaskalebe.data.remote.ApiInterface
import com.jkalebe.androidjeremiaskalebe.domain.models.DataRaw
import com.jkalebe.androidjeremiaskalebe.domain.models.PedidosContainer
import retrofit2.Response

class ApiRepository(val apiInterface: ApiInterface) {

    suspend fun getOrders(): Response<PedidosContainer> {
        return apiInterface.getOrders();
    }

    suspend fun getClient(): Response<DataRaw> {
        return apiInterface.getClient();
    }
}