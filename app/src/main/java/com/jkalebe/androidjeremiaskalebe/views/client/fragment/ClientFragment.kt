package com.jkalebe.androidjeremiaskalebe.views.client.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jkalebe.androidjeremiaskalebe.databinding.FragmentClientBinding
import com.jkalebe.androidjeremiaskalebe.domain.models.Cliente
import com.jkalebe.androidjeremiaskalebe.utils.getCurrentDateTimeFormatted
import com.jkalebe.androidjeremiaskalebe.views.client.ClientViewModel
import com.jkalebe.androidjeremiaskalebe.views.client.ClientViewState
import com.jkalebe.androidjeremiaskalebe.views.client.adapter.ClientAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClientFragment : Fragment() {

    private var _binding: FragmentClientBinding? = null
    private val clientViewModel by viewModel<ClientViewModel>()
    private lateinit var adapter: ClientAdapter
    private val clientId = 30987

    private val binding get() = _binding!!
    private var status: String = "Erro"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnVerify.apply {
            setOnClickListener { showSnackBar(status) }
        }
        setupAdapter()
        setupObserver()
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Dados do cliente"
        lifecycleScope.launch(Dispatchers.IO) { clientViewModel.getClientById(clientId) }
    }

    private fun setupAdapter() {
        adapter = ClientAdapter(listOf())
        binding.let {
            it.rvContact.layoutManager = LinearLayoutManager(requireContext())
            it.rvContact.adapter =
                adapter
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.btnVerify, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun showClient(client: Cliente) {
        status = "${getCurrentDateTimeFormatted()} - Cliente ${client!!.status}"
        lifecycleScope.launch(Dispatchers.Main) {
            val contact = client.contatos
            adapter.updateList(contact)
            binding.apply {
                labelIdNameCompany.text = "${client.id} ${client.razaoSocial}"
                labelNameFantasy.text = "${client.nomeFantasia}"
                labelCnpj.text = client.cnpj
                labelActivity.text = client.ramoAtividade
                labelAddress.text = client.endereco
            }
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            clientViewModel.clientState.collectLatest { viewState ->
                when (viewState) {
                    is ClientViewState.ShowLoading -> toggleLoading(true)
                    is ClientViewState.HideLoading -> toggleLoading(false)
                    is ClientViewState.ShowClient -> {
                        toggleLoading(false)
                        showClient(viewState.client)
                    }

                    is ClientViewState.OnError -> showSnackBar(viewState.message)
                    else -> {}
                }
            }
        }
    }

    private fun toggleLoading(shouldLoad: Boolean) {
        lifecycleScope.launch(Dispatchers.Main){ binding.pbLoading.visibility = if (shouldLoad) View.VISIBLE else View.GONE }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}