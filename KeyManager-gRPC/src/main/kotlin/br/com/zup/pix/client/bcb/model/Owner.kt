package br.com.zup.pix.client.bcb.model

import br.com.zup.pix.client.bcb.model.enums.OwnerType

data class Owner(
    val type: OwnerType,
    val name: String,
    val taxIdNumber: String
){

}
