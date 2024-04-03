package com.jkalebe.androidjeremiaskalebe.domain.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jkalebe.androidjeremiaskalebe.domain.models.Cliente

@Entity(tableName = "Client")
data class ClientEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo("codigo") val codigo: String,
    @ColumnInfo("razaoSocial") val razaoSocial: String,
    @ColumnInfo("nomeFantasia") val nomeFantasia: String,
    @ColumnInfo("cnpj") val cnpj: String,
    @ColumnInfo("ramoAtividade") val ramoAtividade: String,
    @ColumnInfo("endereco") val endereco: String,
    @ColumnInfo("status") val status: String,
)

fun ClientEntity.toCliente(list: List<ContactEntity>): Cliente {
    return Cliente(
        id, codigo, razaoSocial, nomeFantasia, cnpj, ramoAtividade, endereco, status, listOf()
    )
}