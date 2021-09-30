package br.com.zup.pix.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Titular(
    val bancoId: String?,
    val nome: String?,
    val cpf: String?,
){
    @Id
    @GeneratedValue
    val id: Long? = null
}