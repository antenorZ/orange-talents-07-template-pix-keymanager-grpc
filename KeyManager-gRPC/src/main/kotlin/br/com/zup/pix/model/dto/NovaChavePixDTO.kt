package br.com.zup.pix.model.dto

import br.com.zup.pix.model.ChavePix
import br.com.zup.pix.model.ContaAssociada
import br.com.zup.pix.model.enums.*
import br.com.zup.pix.validation.ValidPixKey
import br.com.zup.pix.validation.ValidUUID
import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@ValidPixKey
@Introspected
data class NovaChavePixDTO(
    @field:NotBlank
    @field:ValidUUID
    val clientId: String?,

    @field:NotBlank
    @Enumerated(EnumType.STRING)
    val tipo: TipoChave?,

    @field:Size(max = 77)
    val chave: String?,

    @field:NotBlank
    @Enumerated(EnumType.STRING)
    val tipoConta: TipoConta?
){
    fun toModel(contaAssociada: ContaAssociada): ChavePix {
        return ChavePix(
            pixId = UUID.randomUUID().toString(),
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