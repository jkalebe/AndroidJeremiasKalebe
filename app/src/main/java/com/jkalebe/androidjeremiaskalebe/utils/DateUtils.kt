package com.jkalebe.androidjeremiaskalebe.utils

import java.text.SimpleDateFormat
import java.util.Date

fun getCurrentDateTimeFormatted(): String {
    val date = Date()
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
    return formatter.format(date)
}