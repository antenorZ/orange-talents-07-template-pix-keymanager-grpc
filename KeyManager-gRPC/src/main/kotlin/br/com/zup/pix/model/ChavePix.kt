package br.com.zup.pix.model

import br.com.zup.pix.model.enums.TipoChave
import br.com.zup.pix.model.enums.TipoConta
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
class ChavePix(
    val clientId: String?,
    val tipo: TipoChave?,
    val chave: String?,
    val tipoConta: TipoConta,
    @ManyToOne(cascade = [CascadeType.ALL])
    val conta:ContaAssociada
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
    val criadaEm: LocalDateTime = LocalDateTime.now()
}