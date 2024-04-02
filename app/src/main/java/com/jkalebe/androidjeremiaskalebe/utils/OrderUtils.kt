package com.jkalebe.androidjeremiaskalebe.utils

import com.jkalebe.androidjeremiaskalebe.R

object OrderUtils {

    fun getIconByCriticize(criticize: String?): Int {
        return when (criticize) {
            "SUCESSO" -> R.drawable.ic_success
            "FALHA_PARCIAL" -> R.drawable.ic_partial_failure
            "FALHA_TOTAL" -> R.drawable.ic_total_failure
            else -> R.drawable.ic_awaiting_return
        }
    }

    fun getStatusOrders(status:String):String{
        if (status == "Processado")
            return "L"
        else if(status == "Pendente")
            return "P"
        else if(status == "Cancelado")
            return "C"
        else if(status == "Bloqueado")
            return "B"
        else if(status == "Orçamento")
            return "O"
        else if(status == "Faturado")
            return "F"
        else if(status == "Recusado")
            return "!"
        else if(status == "Montado")
            return "M"
        else
            return "..."
    }
    fun getColorByStatusOrders(status:String): Int {
        if (status == "Processado")
            return R.color.budget_order
        else if(status == "Pendente")
            return R.color.pending_order
        else if(status == "Cancelado")
            return R.color.erp_canceled_order
        else if(status == "Bloqueado")
            return R.color.order_refused
        else if(status == "Orçamento")
            return R.color.budget_order
        else if(status == "Faturado")
            return R.color.invoiced_order
        else if(status == "Recusado")
            return R.color.order_refused
        else if(status == "Montado")
            return R.color.assembled_order
        else
            return R.color.processing_order
    }
}