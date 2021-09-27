package br.com.zup

import br.com.zup.pix.client.itau.ItauClient
import br.com.zup.pix.model.ChavePix
import br.com.zup.pix.model.NovaChavePixDTO
import br.com.zup.pix.repository.ChavePixRepository
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import io.micronaut.grpc.server.GrpcServerChannel
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

@MicronautTest(transactional = false)
internal class RegistraChaveTest(
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

    

    @Factory
    class Clients {
        @Bean
        fun blockingStub(@GrpcChannel(GrpcServerChannel.NAME) channel: ManagedChannel):KeyManagerGRPCServiceGrpc.KeyManagerGRPCServiceBlockingStub{
            return KeyManagerGRPCServiceGrpc.newBlockingStub(channel)
        }
    }
}