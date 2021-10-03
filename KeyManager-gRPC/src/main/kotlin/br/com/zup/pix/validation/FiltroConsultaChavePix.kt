package br.com.zup.pix.validation

import br.com.zup.pix.client.bcb.BcbClient
import br.com.zup.pix.model.ChavePix
import br.com.zup.pix.model.dto.NovaChavePixDTO
import br.com.zup.pix.repository.ChavePixRepository
import io.micronaut.core.annotation.Introspected

@Introspected
sealed class FiltroConsultaChavePix {
    abstract fun filtra(repository: ChavePixRepository, bcbClient: BcbClient): ChavePix

    @Introspected
    data class PorPixId(val clientId: String?, val pixId: String?): FiltroConsultaChavePix(){
        override fun filtra(repository: ChavePixRepository, bcbClient: BcbClient): ChavePix {
            return repository.findByPixId(pixId).get()
        }
    }

    data class PorChave(val chave: String?): FiltroConsultaChavePix(){
        override fun filtra(repository: ChavePixRepository, bcbClient: BcbClient): ChavePix {
            return repository.findByChave(chave).get()
        }
    }

    class Invalido(): FiltroConsultaChavePix(){
        override fun filtra(repository: ChavePixRepository, bcbClient: BcbClient): ChavePix {
            throw IllegalArgumentException("Chave pix invalida ou nao informada")
        }
    }
}