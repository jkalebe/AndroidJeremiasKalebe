package com.jkalebe.androidjeremiaskalebe.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun getCurrentDateTimeFormatted(): String {
    val date = Date()
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
    return formatter.format(date)
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateAndTime(dataHoraStr: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
    val dataHora = ZonedDateTime.parse(dataHoraStr, formatter)
    val agora = ZonedDateTime.now(ZoneId.systemDefault())

    return if (dataHora.toLocalDate() == agora.toLocalDate()) {
        dataHora.format(DateTimeFormatter.ofPattern("HH:mm"))
    } else {
        val mesFormatado = dataHora.format(DateTimeFormatter.ofPattern("MMM", Locale("pt", "BR")))
        "${dataHora.dayOfMonth} de ${mesFormatado.capitalize()}"
    }
}