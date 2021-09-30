package br.com.zup.pix.client.itau.dto.response

import br.com.zup.pix.model.Titular

data class TitularResponse(val id: String, val nome: String, val cpf: String){
    fun toModel(): Titular{
        return Titular(
            bancoId = id,
            nome = nome,
            cpf = cpf
        )
    }
}