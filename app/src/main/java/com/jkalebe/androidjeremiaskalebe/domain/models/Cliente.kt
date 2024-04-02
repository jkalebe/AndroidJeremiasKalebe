package com.jkalebe.androidjeremiaskalebe.domain.models

import com.google.gson.annotations.SerializedName
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
    @SerializedName("contatos") val contatos: List<Contato>
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