package br.com.zup.pix.endpoint

import br.com.zup.KeyManagerListaServiceGrpc
import br.com.zup.ListaChavePixRequest
import br.com.zup.ListaChavePixResponse
import br.com.zup.TipoConta
import br.com.zup.pix.model.enums.TipoChave
import br.com.zup.pix.repository.ChavePixRepository
import io.grpc.stub.StreamObserver
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class ListaChaveEndpoint(@Inject private val repository: ChavePixRepository): KeyManagerListaServiceGrpc.KeyManagerListaServiceImplBase() {
    override fun lista(request: ListaChavePixRequest?, responseObserver: StreamObserver<ListaChavePixResponse>?) {
        if(request?.clientId!!.isBlank()){
            throw IllegalArgumentException("Nao pode ser nulo")
        }

        val clientId = request.clientId

        val chaves = repository.findAllByClientId(clientId).map {
            ListaChavePixResponse.ChavePix.newBuilder()
                .setPixId(it.pixId)
                .setTipo(br.com.zup.TipoChave.valueOf(it.tipo!!.name))
                .setChave(it.chave)
                .setTipoConta(TipoConta.valueOf(it.tipoConta.name))
                .build()
        }

        responseObserver?.onNext(ListaChavePixResponse.newBuilder()
            .setClientId(clientId)
            .addAllChaves(chaves)
            .build())

        responseObserver?.onCompleted()
    }
}