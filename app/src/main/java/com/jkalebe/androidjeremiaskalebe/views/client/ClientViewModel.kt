package com.jkalebe.androidjeremiaskalebe.views.client

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkalebe.androidjeremiaskalebe.data.repository.ApiRepository
import com.jkalebe.androidjeremiaskalebe.data.repository.DataBaseRepository
import com.jkalebe.androidjeremiaskalebe.domain.models.Cliente
import com.jkalebe.androidjeremiaskalebe.domain.models.Pedido
import com.jkalebe.androidjeremiaskalebe.domain.models.database.toContato
import com.jkalebe.androidjeremiaskalebe.domain.models.database.toPedido
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClientViewModel(
    private val dataBaseRepository: DataBaseRepository,
    private val apiRepository: ApiRepository
) : ViewModel() {
    private var _clientState =
        MutableStateFlow<ClientViewState>(value = ClientViewState.OnDefault)
    val clientState: StateFlow<ClientViewState> = _clientState

    private var _ordersState =
        MutableStateFlow<OrdersViewState>(value = OrdersViewState.OnDefault)
    val odersState: StateFlow<OrdersViewState> = _ordersState

    fun getOrdersCallApi(clientId: Int) {
        viewModelScope.launch {
            _ordersState.value = OrdersViewState.ShowLoading
            try {
                val response = withContext(Dispatchers.IO) {
                    apiRepository.getOrders()
                }

                _ordersState.value = OrdersViewState.HideLoading

                if (response.isSuccessful && response.body() != null) {
                    _ordersState.value = OrdersViewState.ShowOrders(response.body()!!.pedidos)
                        insertOrders(
                            response.body()!!.pedidos,
                            clientId = clientId
                        )
                } else {
                    _ordersState.value = OrdersViewState.OnError("Erro ao coletar os pedidos")
                }
            } catch (e: Exception) {
                _ordersState.value = OrdersViewState.HideLoading
                _ordersState.value = OrdersViewState.OnError("Erro: ${e.message}")
            }
        }
    }

    suspend fun getClientCallApi() {
        _clientState.value = ClientViewState.ShowLoading
        try {
            val response = withContext(Dispatchers.IO) {
                apiRepository.getClient()
            }

            _clientState.value = ClientViewState.HideLoading

            if (response.isSuccessful && response.body() != null) {
                _clientState.value = ClientViewState.ShowClient(response.body()!!.cliente)
                withContext(Dispatchers.IO) { insertClient(response.body()!!.cliente) }
            } else {
                _clientState.value = ClientViewState.OnError("Erro ao coletar os dados do cliente")
            }
        } catch (e: Exception) {
            _clientState.value = ClientViewState.HideLoading
            _clientState.value = ClientViewState.OnError("Erro: ${e.message}")
        }
    }

    fun insertClient(cliente: Cliente) {
        viewModelScope.launch {
            try {
                dataBaseRepository.insertClient(cliente)
            } catch (e: Exception) {
                _clientState.value = ClientViewState.OnError("Erro ao salvar cliente no banco: ${e.message}")
            }
        }
    }

    suspend fun insertOrders(pedidos: List<Pedido>, clientId: Int) {
            try {
                pedidos.forEach { pedido ->
                    try{
                        withContext(Dispatchers.IO) {
                            dataBaseRepository.insertOrder(
                                pedido,
                                clientId
                            )
                        }
                    }catch (e:Exception){
                        print(e.message)
                    }
                }
            } catch (e: Exception) {
                _ordersState.value = OrdersViewState.OnError("Erro ao salvar pedidos no banco: ${e.message}")
            }
    }


    fun getClientById(clientId: Int) {
        viewModelScope.launch {
            _clientState.value = ClientViewState.ShowLoading
            dataBaseRepository.getClientById(clientId).collect { client ->
                client?.let {
                    dataBaseRepository.getClientWithContacts(clientId).collect {
                        client.contatos = it.contacts.map { c -> c.toContato() }
                        _clientState.value = ClientViewState.ShowClient(client)
                    }
                } ?: run {
                    _clientState.value = ClientViewState.OnError("Sem clientes")
                    getClientCallApi()
                }
            }
        }
    }

    fun getOrdersByClientId(clientId: Int) {
        viewModelScope.launch {
            _ordersState.value = OrdersViewState.ShowLoading

            val ordersFromDb = withContext(Dispatchers.IO){ dataBaseRepository.getOrdersByClientId(clientId) }
            if (ordersFromDb.isNullOrEmpty()) {
                getOrdersCallApi(clientId)
            } else {
                _ordersState.value = OrdersViewState.ShowOrders(ordersFromDb.map { o -> o.toPedido() })
            }
            _ordersState.value = OrdersViewState.HideLoading
        }
    }
}

sealed class ClientViewState {
    object OnDefault : ClientViewState()
    object ShowLoading : ClientViewState()
    object HideLoading : ClientViewState()
    data class ShowClient(val client: Cliente) : ClientViewState()
    data class OnError(val message: String) : ClientViewState()
}

sealed class OrdersViewState {
    object OnDefault : OrdersViewState()
    object ShowLoading : OrdersViewState()
    object HideLoading : OrdersViewState()
    data class ShowOrders(val orders: List<Pedido>) : OrdersViewState()
    data class OnError(val message: String) : OrdersViewState()
}