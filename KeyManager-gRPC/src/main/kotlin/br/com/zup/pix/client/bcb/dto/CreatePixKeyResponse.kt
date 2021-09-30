package br.com.zup.pix.client.bcb.dto

import br.com.zup.pix.client.bcb.model.BankAccount
import br.com.zup.pix.client.bcb.model.Owner
import br.com.zup.pix.client.bcb.model.enums.PixKeyType
import br.com.zup.pix.client.itau.dto.response.DadosContaResponse
import br.com.zup.pix.client.itau.dto.response.TitularResponse
import br.com.zup.pix.model.enums.TipoChave
import java.time.LocalDateTime

data class CreatePixKeyResponse(
    val key: String,
    val keyType: PixKeyType,
    val bankAccount: BankAccount,
    val owner: Owner,
    val createdAt: LocalDateTime
){

}
