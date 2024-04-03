package com.jkalebe.androidjeremiaskalebe.data.repository

import androidx.lifecycle.LiveData
import com.jkalebe.androidjeremiaskalebe.core.database.ClientDAO
import com.jkalebe.androidjeremiaskalebe.core.database.ContactDAO
import com.jkalebe.androidjeremiaskalebe.core.database.OrderDAO
import com.jkalebe.androidjeremiaskalebe.domain.models.Cliente
import com.jkalebe.androidjeremiaskalebe.domain.models.Pedido
import com.jkalebe.androidjeremiaskalebe.domain.models.database.ClientWithContacts
import com.jkalebe.androidjeremiaskalebe.domain.models.database.ClientWithOrders
import com.jkalebe.androidjeremiaskalebe.domain.models.database.toCliente
import com.jkalebe.androidjeremiaskalebe.domain.models.toClientEntity
import com.jkalebe.androidjeremiaskalebe.domain.models.toContactEntity
import com.jkalebe.androidjeremiaskalebe.domain.models.toOrderEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataBaseRepository(val orderDAO: OrderDAO, val clientDAO: ClientDAO, val contactDAO: ContactDAO,) {
    suspend fun insertClient(cliente: Cliente) {
        val clientEntity = cliente.toClientEntity()
        clientDAO.insertClient(clientEntity)

        cliente.contatos.forEach { contato ->
            val contactEntity = contato.toContactEntity(clientEntity.id)
            contactDAO.insertContact(contactEntity)
        }
    }

    suspend fun insertOrder(pedido: Pedido, clientId: Int) {
        val orderEntity = pedido.toOrderEntity(clientId)
        orderDAO.insertOrder(orderEntity)
    }

    fun getAllClients(): Flow<List<Cliente>> = clientDAO.getAllClients().map { entities ->
        entities.map { it.toCliente(listOf()) }
    }

    fun getClientById(clientId: Int): Flow<Cliente?> = clientDAO.getClientById(clientId).map { it?.toCliente(
        listOf()
    ) }

    fun getClientWithContacts(clientId: Int): Flow<ClientWithContacts> = clientDAO.getClientWithContacts(clientId)

    fun getClientWithOrders(clientId: Int): LiveData<ClientWithOrders> = clientDAO.getClientWithOrders(clientId)

}