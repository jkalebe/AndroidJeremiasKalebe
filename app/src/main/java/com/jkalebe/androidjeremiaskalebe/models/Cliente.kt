package com.jkalebe.androidjeremiaskalebe.models

data class Cliente(
    val id: Int,
    val codigo: String,
    val razao_social: String,
    val nomeFantasia: String,
    val cnpj: String,
    val ramo_atividade: String,
    val endereco: String,
    val status: String,
    val contatos: List<Contato>
)

data class Contato(
    val nome: String,
    val telefone: String,
    val celular: String,
    val conjuge: String?,
    val tipo: String,
    val time: String,
    val e_mail: String,
    val data_nascimento: String,
    val dataNascimentoConjuge: String?
)
