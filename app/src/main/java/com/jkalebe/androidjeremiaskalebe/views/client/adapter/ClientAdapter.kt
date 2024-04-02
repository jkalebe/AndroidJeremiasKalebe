package com.jkalebe.androidjeremiaskalebe.views.client.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jkalebe.androidjeremiaskalebe.databinding.ItemContactBinding
import com.jkalebe.androidjeremiaskalebe.domain.models.Contato

class ClientAdapter(
    private var items: List<Contato>,
) : RecyclerView.Adapter<ClientAdapter.ViewHolder>() {

    fun updateList(list: List<Contato>){
        items = list
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contato) {
            binding.apply {
                labelNameClient.text = contact.nome
                labelPhone.text = contact.telefone
                labelEmail.text = contact.email
                labelSmartphone.text = contact.celular
                labelDateBirth.text = contact.dataNascimento
                labelType.text = contact.tipo
                labelSoccerClub.text = contact.time
                labelSoccerClub2.text = contact.time
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(contact = item)
    }

    override fun getItemCount() = items.size
}
