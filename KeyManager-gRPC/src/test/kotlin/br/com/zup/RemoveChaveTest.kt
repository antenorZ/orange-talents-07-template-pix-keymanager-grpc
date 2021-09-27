package br.com.zup

import br.com.zup.pix.client.itau.ItauClient
import br.com.zup.pix.repository.ChavePixRepository
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.BeforeEach

@MicronautTest(transactional = false)
internal class RemoveChaveTest(
    val repository: ChavePixRepository,
    val grpcClient: KeyManagerRemoveGRPCServiceGrpc.KeyManagerRemoveGRPCServiceBlockingStub)
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
}