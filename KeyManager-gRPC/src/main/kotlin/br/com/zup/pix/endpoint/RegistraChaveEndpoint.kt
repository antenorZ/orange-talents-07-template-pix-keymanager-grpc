package br.com.zup.pix.endpoint

import br.com.zup.KeyManagerGRPCServiceGrpc
import br.com.zup.RegistraChavePixRequest
import br.com.zup.RegistraChavePixResponse
import br.com.zup.pix.extensions.toModel
import br.com.zup.pix.service.NovaChavePixService
import io.grpc.stub.StreamObserver
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class RegistraChaveEndpoint(@Inject private val service: NovaChavePixService): KeyManagerGRPCServiceGrpc.KeyManagerGRPCServiceImplBase(){
    override fun registraChavePix(
        request: RegistraChavePixRequest?,
        responseObserver: StreamObserver<RegistraChavePixResponse>?
    ){
        val novaChave = request!!.toModel()
        val chaveCriada = service.registra(novaChave)

        responseObserver!!.onNext(RegistraChavePixResponse.newBuilder()
                                .setClientId(chaveCriada.clientId.toString())
                                .setPixKey(chaveCriada.chave)
                                .build())
        responseObserver!!.onCompleted()
    }
}