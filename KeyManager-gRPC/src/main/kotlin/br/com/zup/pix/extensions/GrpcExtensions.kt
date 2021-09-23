package br.com.zup.pix.extensions

import br.com.zup.RegistraChavePixRequest
import br.com.zup.TipoChave
import br.com.zup.TipoConta
import br.com.zup.pix.model.NovaChavePixDTO


fun RegistraChavePixRequest.toModel(): NovaChavePixDTO{
    return NovaChavePixDTO(
        clientId = clientId,
        tipo = when(tipoChave){
            TipoChave.UNKNOWN_TIPO_CHAVE -> null
            else -> br.com.zup.pix.model.enums.TipoChave.valueOf(tipoChave.name)
        },
        chave = chave,
        tipoConta = when(tipoConta){
            TipoConta.UNKNOWN_TIPO_CONTA -> null
            else -> br.com.zup.pix.model.enums.TipoConta.valueOf(tipoConta.name)
        }
    )
}