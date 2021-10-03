package br.com.zup

import br.com.zup.pix.client.itau.ItauClient
import br.com.zup.pix.model.ChavePix
import br.com.zup.pix.model.ContaAssociada
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
internal class RegistraChaveEndpointTest(
    val repository: ChavePixRepository,
    val grpcClient: KeyManagerGRPCServiceGrpc.KeyManagerGRPCServiceBlockingStub
){
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
    fun `deve registrar nova chave pix`(){
        val response: RegistraChavePixResponse = grpcClient.registraChavePix(
            RegistraChavePixRequest.newBuilder().
            setClientId(CLIENT_ID).
            setTipoChave(TipoChave.CPF).
            setChave("02467781054").
            setTipoConta(TipoConta.CONTA_CORRENTE).
            build()
        )
        assertNotNull(response.pixKey)
        assertTrue(repository.existsByChave("02467781054"))
    }

    @Test
    fun `nao cadastra chave repetida`(){
        repository.save(ChavePix(
            pixId = UUID.randomUUID().toString(),
            clientId = CLIENT_ID,
            tipo = br.com.zup.pix.model.enums.TipoChave.CPF,
            chave = "02467781054",
            tipoConta = br.com.zup.pix.model.enums.TipoConta.CONTA_CORRENTE,
            conta = ContaAssociada(
                instituicao = "ITAU",
                nomeTitular = "Rafael M C Ponte",
                cpfTitular = "02467781054",
                agencia = "0001",
                numeroConta = "291900",
            ))
        )

        val erro = assertThrows<StatusRuntimeException>{
            grpcClient.registraChavePix(RegistraChavePixRequest.newBuilder().
            setClientId(CLIENT_ID).
            setTipoChave(TipoChave.CPF).
            setChave("02467781054").
            setTipoConta(TipoConta.CONTA_CORRENTE).
            build())
        }
        assertEquals(Status.UNKNOWN.code, erro.status.code)
    }

    @Test
    fun `nao cadastra chave pix vazia`(){
        val erro = assertThrows<StatusRuntimeException>{
            grpcClient.registraChavePix(RegistraChavePixRequest.newBuilder().
            setClientId(CLIENT_ID).
            setTipoChave(TipoChave.CPF).
            setChave("").
            setTipoConta(TipoConta.CONTA_CORRENTE).
            build())
        }
        assertEquals(Status.UNKNOWN.code, erro.status.code)
    }

    @Test
    fun `nao cadastra chave pix formato invalido`(){
        val erro = assertThrows<StatusRuntimeException>{
            grpcClient.registraChavePix(RegistraChavePixRequest.newBuilder().
            setClientId(CLIENT_ID).
            setTipoChave(TipoChave.CPF).
            setChave("aaaa").
            setTipoConta(TipoConta.CONTA_CORRENTE).
            build())
        }
        assertEquals(Status.UNKNOWN.code, erro.status.code)
    }

    @Test
    fun `nao cadastra chave pix caso o cliente nao for encontrado`(){
        val erro = assertThrows<StatusRuntimeException>{
            grpcClient.registraChavePix(RegistraChavePixRequest.newBuilder().
            setClientId("aaaa").
            setTipoChave(TipoChave.CPF).
            setChave("02467781054").
            setTipoConta(TipoConta.CONTA_CORRENTE).
            build())
        }
        assertEquals(Status.UNKNOWN.code, erro.status.code)
    }


    @Factory
    class Clients {
        @Bean
        fun blockingStub(@GrpcChannel(GrpcServerChannel.NAME) channel: ManagedChannel):KeyManagerGRPCServiceGrpc.KeyManagerGRPCServiceBlockingStub{
            return KeyManagerGRPCServiceGrpc.newBlockingStub(channel)
        }
    }
}