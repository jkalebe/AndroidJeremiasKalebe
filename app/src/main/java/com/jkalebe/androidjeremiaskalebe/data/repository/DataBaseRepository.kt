package com.jkalebe.androidjeremiaskalebe.data.repository

import com.jkalebe.androidjeremiaskalebe.core.database.ClientDAO
import com.jkalebe.androidjeremiaskalebe.core.database.ContactDAO
import com.jkalebe.androidjeremiaskalebe.core.database.OrderDAO

class DataBaseRepository(val orderDAO: OrderDAO, val clientDAO: ClientDAO, val contactDAO: ContactDAO) {
}