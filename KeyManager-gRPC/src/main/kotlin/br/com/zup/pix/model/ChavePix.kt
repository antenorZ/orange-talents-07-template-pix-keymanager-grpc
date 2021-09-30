package br.com.zup.pix.model

import br.com.zup.pix.client.bcb.dto.CreatePixKeyResponse
import br.com.zup.pix.model.enums.TipoChave
import br.com.zup.pix.model.enums.TipoConta
import br.com.zup.pix.validation.ValidUUID
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
//@ValidPixKey
class ChavePix(
    @field:NotNull
    @field:ValidUUID
    val clientId: String?,

    @field:NotNull
    val tipo: TipoChave?,

    @field:NotBlank
    var chave: String?,

    @field:NotNull
    val tipoConta: TipoConta,

    @field:Valid
    @ManyToOne(cascade = [CascadeType.ALL])
    val conta:ContaAssociada
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
    val criadaEm: LocalDateTime = LocalDateTime.now()


    fun atualizaChave(createPixKeyResponse: String){
        this.chave = createPixKeyResponse
    }
}