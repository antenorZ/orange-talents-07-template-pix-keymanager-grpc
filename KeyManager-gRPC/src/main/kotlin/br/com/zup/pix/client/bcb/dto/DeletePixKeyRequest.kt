package br.com.zup.pix.client.bcb.dto

import br.com.zup.pix.client.bcb.model.BankAccount
import br.com.zup.pix.client.bcb.model.Owner
import br.com.zup.pix.client.bcb.model.enums.AccountType
import br.com.zup.pix.client.bcb.model.enums.OwnerType
import br.com.zup.pix.client.bcb.model.enums.PixKeyType
import br.com.zup.pix.model.ChavePix
import br.com.zup.pix.model.ContaAssociada

data class DeletePixKeyRequest(
    val key: String?,
    val participant: String?
) {
    companion object{
            fun of(chave: ChavePix): DeletePixKeyRequest{
                return DeletePixKeyRequest(
                    key = chave.chave,
                    participant = ContaAssociada.ITAU_UNIBANCO_ISPB
                )
            }
    }
}