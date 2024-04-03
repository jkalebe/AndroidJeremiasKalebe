package com.jkalebe.androidjeremiaskalebe.domain.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.jkalebe.androidjeremiaskalebe.domain.models.Contato

@Entity(tableName = "Contact", foreignKeys = [ForeignKey(entity = ClientEntity::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("clientId"),
    onDelete = ForeignKey.CASCADE)])
data class ContactEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "clientId") val clientId: Int,
    @ColumnInfo("nome") val nome: String,
    @ColumnInfo("telefone") val telefone: String,
    @ColumnInfo("celular") val celular: String,
    @ColumnInfo("conjuge") val conjuge: String?,
    @ColumnInfo("tipo") val tipo: String,
    @ColumnInfo("time") val time: String,
    @ColumnInfo("email") val email: String,
    @ColumnInfo("dataNascimento") val dataNascimento: String,
    @ColumnInfo("dataNascimentoConjuge") val dataNascimentoConjuge: String?
)

fun ContactEntity.toContato(): Contato {
    return Contato(
        nome, telefone, celular, conjuge, tipo, time, email, dataNascimento, dataNascimentoConjuge
    )
}