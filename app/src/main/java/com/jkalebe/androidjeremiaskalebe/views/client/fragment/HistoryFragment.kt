package com.jkalebe.androidjeremiaskalebe.views.client.fragment

import android.app.Activity
import android.app.Dialog
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jkalebe.androidjeremiaskalebe.R
import com.jkalebe.androidjeremiaskalebe.databinding.DialogSubtitlesBinding
import com.jkalebe.androidjeremiaskalebe.databinding.FragmentHistoryBinding
import com.jkalebe.androidjeremiaskalebe.domain.models.Pedido
import com.jkalebe.androidjeremiaskalebe.views.client.ClientViewModel
import com.jkalebe.androidjeremiaskalebe.views.client.OrdersViewState
import com.jkalebe.androidjeremiaskalebe.views.client.adapter.OrderAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private var subtitlesDialog: Dialog? = null
    private val binding get() = _binding!!
    private val clientViewModel by viewModel<ClientViewModel>()
    private lateinit var adapter: OrderAdapter
    private val clientId = 30987
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
        clientViewModel.getOrdersByClientId(clientId)
        setupAdapter()
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Hist. de pedidos"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.pbLoading, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun setupAdapter() {
        adapter = OrderAdapter(requireContext(), listOf())
        binding.let {
            it.rvOrders.layoutManager = LinearLayoutManager(requireContext())
            it.rvOrders.adapter =
                adapter
        }
    }

    private fun showOrders(orders: List<Pedido>) {
        lifecycleScope.launch(Dispatchers.Main){
            if (::adapter.isInitialized) {
                adapter.updateList(orders)
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()

        inflater.inflate(R.menu.fragment_hist_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_legends -> {
                showSubtitlesDialog(requireActivity())
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
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
        lifecycleScope.launch(Dispatchers.Main){ binding.pbLoading.visibility = if (shouldLoad) View.VISIBLE else View.GONE }
    }

    fun showSubtitlesDialog(activity: Activity) {
        if (activity.isFinishing || activity.isDestroyed) {
            return
        }

        val layoutInflater = LayoutInflater.from(activity)
        val binding: DialogSubtitlesBinding = DialogSubtitlesBinding.inflate(layoutInflater)

        binding.apply {
            inProcessed.background.setColorFilter(getResources().getColor(R.color.processing_order), PorterDuff.Mode.SRC_ATOP)
            refused.background.setColorFilter(getResources().getColor(R.color.order_refused), PorterDuff.Mode.SRC_ATOP)
            pending.background.setColorFilter(getResources().getColor(R.color.pending_order), PorterDuff.Mode.SRC_ATOP)
            blocked.background.setColorFilter(getResources().getColor(R.color.blocked_order), PorterDuff.Mode.SRC_ATOP)
            released.background.setColorFilter(getResources().getColor(R.color.released_order), PorterDuff.Mode.SRC_ATOP)
            assembled.background.setColorFilter(getResources().getColor(R.color.assembled_order), PorterDuff.Mode.SRC_ATOP)
            invoiced.background.setColorFilter(getResources().getColor(R.color.invoiced_order), PorterDuff.Mode.SRC_ATOP)
            canceled.background.setColorFilter(getResources().getColor(R.color.canceled_order), PorterDuff.Mode.SRC_ATOP)
            budget.background.setColorFilter(getResources().getColor(R.color.budget_order), PorterDuff.Mode.SRC_ATOP)

        }

        subtitlesDialog =  AlertDialog.Builder(activity)
            .setView(binding.root)
            .setNegativeButton("Fechar") { dialog, _ -> dialog.dismiss() }
            .create()

        subtitlesDialog?.show()
    }


    override fun onDestroyView() {
        subtitlesDialog?.dismiss()
        subtitlesDialog = null
        super.onDestroyView()
        _binding = null
    }

}