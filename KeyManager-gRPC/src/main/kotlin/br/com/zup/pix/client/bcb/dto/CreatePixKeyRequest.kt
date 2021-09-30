package br.com.zup.pix.client.bcb.dto

import br.com.zup.pix.client.bcb.model.BankAccount
import br.com.zup.pix.client.bcb.model.Owner
import br.com.zup.pix.client.bcb.model.enums.AccountType
import br.com.zup.pix.client.bcb.model.enums.OwnerType
import br.com.zup.pix.client.bcb.model.enums.PixKeyType
import br.com.zup.pix.model.ChavePix
import br.com.zup.pix.model.ContaAssociada

data class CreatePixKeyRequest(
    val keyType: PixKeyType,
    val key: String?,
    val bankAccount: BankAccount,
    val owner: Owner
){
    companion object{
        fun of(chave: ChavePix): CreatePixKeyRequest{
            return CreatePixKeyRequest(
                keyType = PixKeyType.by(chave.tipo),
                key = chave.chave,
                bankAccount = BankAccount(
                    participant = ContaAssociada.ITAU_UNIBANCO_ISPB,
                    branch = chave.conta.agencia,
                    accountNumber = chave.conta.numeroConta,
                    accountType = AccountType.by(chave.tipoConta)
                ),
                owner = Owner(
                    type = OwnerType.NATURAL_PERSON,
                    name = chave.conta.nomeTitular,
                    taxIdNumber = chave.conta.cpfTitular
                )
            )
        }
    }
}
