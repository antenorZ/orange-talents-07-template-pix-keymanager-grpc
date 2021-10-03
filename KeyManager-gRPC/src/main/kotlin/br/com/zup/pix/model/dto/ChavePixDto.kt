package br.com.zup.pix.model.dto

data class ChavePixDto(
    val pixId: String,

    val clientId: String?,

    val chave: String?,

    val nomeTitular: String?,

    val tipoChave: String
){

}