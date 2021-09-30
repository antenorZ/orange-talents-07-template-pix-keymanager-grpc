package br.com.zup.pix.client.bcb.model

import br.com.zup.pix.client.bcb.model.enums.AccountType

data class BankAccount(
    val participant: String,
    val branch: String,
    val accountNumber: String,
    val accountType: AccountType
) {

}
