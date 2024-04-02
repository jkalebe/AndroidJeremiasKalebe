package com.jkalebe.androidjeremiaskalebe.views.client.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.jkalebe.androidjeremiaskalebe.databinding.FragmentHistoryBinding
import com.jkalebe.androidjeremiaskalebe.domain.models.Pedido
import com.jkalebe.androidjeremiaskalebe.views.client.ClientViewModel
import com.jkalebe.androidjeremiaskalebe.views.client.ClientViewState
import com.jkalebe.androidjeremiaskalebe.views.client.OrdersViewState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val clientViewModel by viewModel<ClientViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        clientViewModel.getOrders()
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.pbLoading, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun showOrders(orders: List<Pedido>) {

    }

    private fun setupObserver(){
        lifecycleScope.launch{
            clientViewModel.odersState.collect { viewState ->
                when (viewState) {
                    is OrdersViewState.ShowLoading -> toggleLoading(true)
                    is OrdersViewState.HideLoading -> toggleLoading(false)
                    is OrdersViewState.ShowOrders -> showOrders(viewState.orders)
                    is OrdersViewState.OnError -> showSnackBar(viewState.message)
                    else -> {}
                }
            }
        }
    }

    private fun toggleLoading(shouldLoad: Boolean) {
        binding.pbLoading.visibility = if (shouldLoad) View.VISIBLE else View.GONE
    }

}