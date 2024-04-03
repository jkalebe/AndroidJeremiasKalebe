package com.jkalebe.androidjeremiaskalebe.domain.models

import com.google.gson.annotations.SerializedName
import com.jkalebe.androidjeremiaskalebe.domain.models.database.ClientEntity
import com.jkalebe.androidjeremiaskalebe.domain.models.database.ContactEntity
import java.io.Serializable

data class Cliente(
    @SerializedName("id") val id: Int,
    @SerializedName("codigo") val codigo: String,
    @SerializedName("razao_social") val razaoSocial: String,
    @SerializedName("nomeFantasia") val nomeFantasia: String,
    @SerializedName("cnpj") val cnpj: String,
    @SerializedName("ramo_atividade") val ramoAtividade: String,
    @SerializedName("endereco") val endereco: String,
    @SerializedName("status") val status: String,
    @SerializedName("contatos") var contatos: List<Contato>
): Serializable

data class Contato(
    @SerializedName("nome") val nome: String,
    @SerializedName("telefone") val telefone: String,
    @SerializedName("celular") val celular: String,
    @SerializedName("conjuge") val conjuge: String?,
    @SerializedName("tipo") val tipo: String,
    @SerializedName("time") val time: String,
    @SerializedName("e_mail") val email: String,
    @SerializedName("data_nascimento") val dataNascimento: String,
    @SerializedName("dataNascimentoConjuge") val dataNascimentoConjuge: String?
): Serializable

data class DataRaw(
    @SerializedName("cliente") val cliente: Cliente,
)

fun Cliente.toClientEntity(): ClientEntity {
    return ClientEntity(
        id = this.id,
        codigo = this.codigo,
        razaoSocial = this.razaoSocial,
        nomeFantasia = this.nomeFantasia,
        cnpj = this.cnpj,
        ramoAtividade = this.ramoAtividade,
        endereco = this.endereco,
        status = this.status,
    )
}

fun Contato.toContactEntity(clientId: Int): ContactEntity {
    return ContactEntity(
        id = 0, // Assumindo autoGenerate = true para o ID
        clientId = clientId,
        nome = this.nome,
        telefone = this.telefone,
        celular = this.celular,
        conjuge = this.conjuge,
        tipo = this.tipo,
        time = this.time,
        email = this.email,
        dataNascimento = this.dataNascimento,
        dataNascimentoConjuge = this.dataNascimentoConjuge
    )
}
