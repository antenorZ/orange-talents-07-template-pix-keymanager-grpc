package br.com.zup.pix.service

import br.com.zup.pix.client.itau.ItauClient
import br.com.zup.pix.repository.ChavePixRepository
import br.com.zup.pix.validation.ValidUUID
import io.micronaut.core.async.annotation.SingleResult
import jakarta.inject.Inject
import jakarta.inject.Singleton
import net.bytebuddy.implementation.bytecode.Throw
import java.lang.IllegalStateException
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank

@Singleton
class RemoveChaveService(@Inject val chavePixRepository: ChavePixRepository,
                         @Inject val itauClient: ItauClient)
{
    @Transactional
    fun remove(
        @NotBlank @ValidUUID(message = "Formato de ID invalido") clientId: String?,
        @NotBlank (message = "Chave PIX no formato invalido") pixKey: String?
    ){
        val possivelConta = itauClient.buscaConta(clientId).body() ?:
            throw IllegalStateException("Cliente não encontrado no Itau")

        val possivelChave = chavePixRepository.findByChaveAndClientId(pixKey, clientId)

        if(!possivelChave.isPresent){
            throw IllegalStateException("Chave nao encontrada")
        }
        chavePixRepository.delete(possivelChave.get())
    }
}