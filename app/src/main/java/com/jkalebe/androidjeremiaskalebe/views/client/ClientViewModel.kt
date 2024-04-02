package com.jkalebe.androidjeremiaskalebe.views.client

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkalebe.androidjeremiaskalebe.data.repository.ApiRepository
import com.jkalebe.androidjeremiaskalebe.domain.models.Cliente
import com.jkalebe.androidjeremiaskalebe.domain.models.Pedido
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClientViewModel(val apiRepository: ApiRepository) : ViewModel() {
    private var _clientState =
        MutableStateFlow<ClientViewState>(value = ClientViewState.OnDefault)
    val clientState: StateFlow<ClientViewState> = _clientState

    private var _ordersState =
        MutableStateFlow<OrdersViewState>(value = OrdersViewState.OnDefault)
    val odersState: StateFlow<OrdersViewState> = _ordersState

    fun getOrders(){
        viewModelScope.launch {
            _ordersState.value = OrdersViewState.ShowLoading
            try{
                val response = withContext(Dispatchers.IO) {
                    apiRepository.getOrders()
                }

                _ordersState.value = OrdersViewState.HideLoading

                if (response.isSuccessful && response.body() != null) {
                    _ordersState.value = OrdersViewState.ShowOrders(response.body()!!.pedidos)
                } else {
                    _ordersState.value = OrdersViewState.OnError("Erro ao coletar os pedidos")
                }
            }catch (e: Exception){
                _ordersState.value = OrdersViewState.HideLoading
                _ordersState.value = OrdersViewState.OnError("Erro: ${e.message}")
            }
        }
    }

    suspend fun getClient() {
            _clientState.value = ClientViewState.ShowLoading
            try {
                val response = withContext(Dispatchers.IO) {
                    apiRepository.getClient()
                }

                _clientState.value = ClientViewState.HideLoading

                if (response.isSuccessful && response.body() != null) {
                    _clientState.value = ClientViewState.ShowClient(response.body()!!.cliente)
                } else {
                    _clientState.value = ClientViewState.OnError("Erro ao coletar os dados do cliente")
                }
            } catch (e: Exception) {
                _clientState.value = ClientViewState.HideLoading
                _clientState.value = ClientViewState.OnError("Erro: ${e.message}")
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