package br.com.zup.pix.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
class ContaAssociada(
    @field:NotBlank
    val instituicao: String,

    @field:NotBlank
    val nomeTitular: String,

    @field:NotBlank
    val cpfTitular: String,

    @field:NotBlank
    val agencia: String,

    @field:NotBlank
    val numeroConta: String
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    companion object {
        public val ITAU_UNIBANCO_ISPB: String = "60701190"
    }
}