package com.jkalebe.androidjeremiaskalebe.views.client.adapter

import android.content.Context
import android.graphics.PorterDuff
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jkalebe.androidjeremiaskalebe.databinding.ItemOrderBinding
import com.jkalebe.androidjeremiaskalebe.domain.models.Pedido
import com.jkalebe.androidjeremiaskalebe.utils.OrderUtils
import com.jkalebe.androidjeremiaskalebe.utils.OrderUtils.getColorByStatusOrders
import com.jkalebe.androidjeremiaskalebe.utils.formatDateAndTime

class OrderAdapter(
    private val context: Context,
    private var items: List<Pedido>,
) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    fun updateList(list: List<Pedido>){
        items = list
        notifyDataSetChanged()

    }

    class ViewHolder(val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(context: Context, pedido: Pedido) {
            val statusColor = getColorByStatusOrders(pedido.status)
            val colorInt = ContextCompat.getColor(context, statusColor)
            binding.apply {
                tvInitial.background.setColorFilter(colorInt, PorterDuff.Mode.SRC_IN)
                tvInitial.text = OrderUtils.getStatusOrders(pedido.status)
                labelRcaErp.text = "${ pedido.numero_ped_Rca } / ${ pedido.numero_ped_erp }"
                labelStatusOrder.text = formatDateAndTime(pedido.data)
                labelIdName.text = "${pedido.codigoCliente} - ${pedido.NOMECLIENTE}"
                labelStatusOrder.text = pedido.status
                labelValueOrder.text = "R$ --.--"
                val icon = OrderUtils.getIconByCriticize(pedido.critica)
                Glide.with(context).load(icon)
                    .into(tvIconeCriticize)

                if(pedido.legendas.isNullOrEmpty()){
                    tvIconSubtitles.visibility = View.GONE
                } else {
                    tvIconSubtitles.visibility = View.VISIBLE
                    val icon = OrderUtils.getIconBySubtitle(pedido.legendas.first())
                    Glide.with(context).load(icon)
                        .into(tvIconSubtitles)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOrderBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(context = context, pedido = item)
    }

    override fun getItemCount() = items.size
}
