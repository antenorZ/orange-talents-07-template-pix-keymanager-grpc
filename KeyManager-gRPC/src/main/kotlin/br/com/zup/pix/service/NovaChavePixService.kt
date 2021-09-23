package br.com.zup.pix.service

import br.com.zup.pix.client.itau.ItauClient
import br.com.zup.pix.model.ChavePix
import br.com.zup.pix.model.NovaChavePixDTO
import br.com.zup.pix.repository.ChavePixRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.lang.IllegalStateException
import javax.transaction.Transactional
import javax.validation.Valid

@Singleton
class NovaChavePixService(@Inject val itauClient: ItauClient,
                          @Inject val chavePixRepository: ChavePixRepository){


    fun registra(@Valid novaChave: NovaChavePixDTO): ChavePix{


        val dadosContaResponse = itauClient.
            buscaContaPorTipo(novaChave.clientId!!, novaChave.tipoConta!!.name)

        val contaEncontrada = dadosContaResponse.body()?.toModel() ?:
            throw IllegalStateException("Cliente não encontrado no Itau")

        val chaveGerada = novaChave.toModel(contaEncontrada)

        chavePixRepository.save(chaveGerada)

        return chaveGerada
    }
}
