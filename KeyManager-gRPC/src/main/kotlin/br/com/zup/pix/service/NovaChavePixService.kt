package br.com.zup.pix.service

import br.com.zup.pix.client.bcb.BcbClient
import br.com.zup.pix.client.bcb.dto.CreatePixKeyRequest
import br.com.zup.pix.client.itau.ItauClient
import br.com.zup.pix.model.ChavePix
import br.com.zup.pix.model.dto.NovaChavePixDTO
import br.com.zup.pix.repository.ChavePixRepository
import io.micronaut.http.HttpStatus
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.lang.IllegalStateException
import javax.transaction.Transactional
import javax.validation.Valid

@Singleton
class NovaChavePixService(@Inject val itauClient: ItauClient,
                          @Inject val bcbClient: BcbClient,
                          @Inject val chavePixRepository: ChavePixRepository){

    @Transactional
    fun registra(@Valid novaChave: NovaChavePixDTO): ChavePix{

        if(chavePixRepository.existsByChave(novaChave.chave))
            throw IllegalStateException("A chave inserida já existe no banco de dados")

        val dadosContaResponse = itauClient.
            buscaContaPorTipo(novaChave.clientId!!, novaChave.tipoConta!!.name)

        val contaEncontrada = dadosContaResponse.body()?.toModel() ?:
            throw IllegalStateException("Cliente não encontrado no Itau")

        val chaveGerada = novaChave.toModel(contaEncontrada)

        chavePixRepository.save(chaveGerada)

        val bcbRequest = CreatePixKeyRequest.of(chaveGerada)

        val bcbResponse = bcbClient.cadastraChave(bcbRequest)

        if(bcbResponse.status != HttpStatus.CREATED)
            throw IllegalStateException("Não foi possivel gerar a chave no bacen")

        chavePixRepository.save(chaveGerada)

        chaveGerada.atualizaChave(bcbResponse.body().key)

        return chaveGerada
    }
}
