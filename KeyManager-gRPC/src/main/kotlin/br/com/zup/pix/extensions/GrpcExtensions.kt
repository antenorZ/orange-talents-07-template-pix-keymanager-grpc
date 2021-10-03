package br.com.zup.pix.extensions

import br.com.zup.CarregaChavePixRequest
import br.com.zup.RegistraChavePixRequest
import br.com.zup.TipoChave
import br.com.zup.TipoConta
import br.com.zup.pix.model.dto.NovaChavePixDTO
import br.com.zup.pix.validation.FiltroConsultaChavePix


fun RegistraChavePixRequest.toModel(): NovaChavePixDTO {
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

fun CarregaChavePixRequest.toModel(): FiltroConsultaChavePix{
    val filtro = when(filtroCase){
        CarregaChavePixRequest.FiltroCase.PIXID -> pixId.let {
            FiltroConsultaChavePix.PorPixId(clientId = it.clientId, pixId = it.pixId)
        }

        CarregaChavePixRequest.FiltroCase.CHAVE -> FiltroConsultaChavePix.PorChave(chave)

        CarregaChavePixRequest.FiltroCase.FILTRO_NOT_SET -> FiltroConsultaChavePix.Invalido()
    }

    return filtro
}

