package br.com.zup.pix.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class ContaAssociada(
    val instituicao: String,
    val nomeTitular: String,
    val cpfTitular: String,
    val agencia: String,
    val numeroConta: String
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    companion object {
        public val ITAU_UNIBANCO_ISPB: String = "60701190"
    }
}