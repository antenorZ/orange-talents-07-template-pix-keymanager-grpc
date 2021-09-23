package br.com.zup.pix.model

import br.com.zup.pix.model.enums.*
import io.micronaut.core.annotation.Introspected
import java.util.*

@Introspected
data class NovaChavePixDTO(
    val clientId: String?,
    val tipo: TipoChave?,
    val chave: String?,
    val tipoConta: TipoConta?
){
    fun toModel(contaAssociada: ContaAssociada):ChavePix{
        return ChavePix(
            clientId = this.clientId,
            tipo = TipoChave.valueOf(this.tipo!!.name),
            chave = if (this.tipo == TipoChave.ALEATORIA)
                        UUID.randomUUID().toString()
                    else this.chave!!,
            tipoConta = TipoConta.valueOf(this.tipoConta!!.name),
            conta = contaAssociada
        )
    }
}