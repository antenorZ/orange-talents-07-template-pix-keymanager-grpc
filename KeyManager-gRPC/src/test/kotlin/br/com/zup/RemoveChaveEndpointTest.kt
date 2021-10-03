package br.com.zup

import br.com.zup.pix.client.itau.ItauClient
import br.com.zup.pix.model.ChavePix
import br.com.zup.pix.model.ContaAssociada
import br.com.zup.pix.model.enums.TipoChave
import br.com.zup.pix.model.enums.TipoConta
import br.com.zup.pix.repository.ChavePixRepository
import io.grpc.ManagedChannel
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import io.micronaut.grpc.server.GrpcServerChannel
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

@MicronautTest(transactional = false)
internal class RemoveChaveEndpointTest(
    val repository: ChavePixRepository,
    val grpcClient: KeyManagerRemoveGRPCServiceGrpc.KeyManagerRemoveGRPCServiceBlockingStub
    )
{
    @Inject
    lateinit var itauClient: ItauClient;

    companion object{
        val CLIENT_ID = "c56dfef4-7901-44fb-84e2-a2cefb157890"
    }

    @BeforeEach
    fun setup(){
        repository.deleteAll()
    }

    @Test
    fun `deve remover chave pix`(){
        repository.save(
            ChavePix(
            pixId = UUID.randomUUID().toString(),
            clientId = RegistraChaveEndpointTest.CLIENT_ID,
            tipo = TipoChave.CPF,
            chave = "02467781054",
            tipoConta = TipoConta.CONTA_CORRENTE,
            conta = ContaAssociada(
                instituicao = "ITAU",
                nomeTitular = "Rafael M C Ponte",
                cpfTitular = "02467781054",
                agencia = "0001",
                numeroConta = "291900",
            )
            )
        )

        assertTrue(repository.existsByChave("02467781054"))

        grpcClient.remove(RemoveChavePixRequest.newBuilder()
            .setPixKey("02467781054")
            .setClientId("c56dfef4-7901-44fb-84e2-a2cefb157890")
            .build())

        assertFalse(repository.existsByChave("02467781054"))
    }

    @Test
    fun `nao deve remover chave inexistente`(){
        val erro = assertThrows<StatusRuntimeException>{
            grpcClient.remove(
                RemoveChavePixRequest.newBuilder()
                    .setPixKey("02467781054")
                    .setClientId("c56dfef4-7901-44fb-84e2-a2cefb157890")
                    .build()
            )
        }
        assertEquals(Status.UNKNOWN.code, erro.status.code)
    }

    @Test
    fun `nao deve remover chave caso o cliente nao for encontrado`(){
        val erro = assertThrows<StatusRuntimeException>{

            val clienteId = UUID.randomUUID().toString()

            itauClient.buscaConta(clienteId).body()

            grpcClient.remove(
                RemoveChavePixRequest.newBuilder()
                    .setPixKey("02467781054")
                    .setClientId(clienteId)
                    .build()
            )
        }
        assertEquals(Status.UNKNOWN.code, erro.status.code)
    }

    @Test
    fun `nao deve remover chave com formato invalido`(){
        val erro = assertThrows<StatusRuntimeException>{
            grpcClient.remove(
                RemoveChavePixRequest.newBuilder()
                    .setPixKey("0246778105A")
                    .setClientId("c56dfef4-7901-44fb-84e2-a2cefb157890")
                    .build()
            )
        }
        assertEquals(Status.UNKNOWN.code, erro.status.code)
    }

    @Factory
    class Clients {
        @Bean
        fun blockingStub(@GrpcChannel(GrpcServerChannel.NAME) channel: ManagedChannel):KeyManagerRemoveGRPCServiceGrpc.KeyManagerRemoveGRPCServiceBlockingStub{
            return KeyManagerRemoveGRPCServiceGrpc.newBlockingStub(channel)
        }
    }
}