package br.com.zup.pix.endpoint

import br.com.zup.KeyManagerRemoveGRPCServiceGrpc
import br.com.zup.RemoveChavePixRequest
import br.com.zup.RemoveChavePixResponse
import br.com.zup.pix.service.RemoveChaveService
import io.grpc.stub.StreamObserver
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class RemoveChave(@Inject private val service: RemoveChaveService): KeyManagerRemoveGRPCServiceGrpc.KeyManagerRemoveGRPCServiceImplBase(){
    override fun remove(request: RemoveChavePixRequest, responseObserver: StreamObserver<RemoveChavePixResponse>) {

        service.remove(clientId = request.clientId, pixKey = request.pixKey)

        responseObserver.onNext(RemoveChavePixResponse.newBuilder()
                                .setClientId(request.clientId)
                                .setPixKey(request.pixKey)
                                .build())

        responseObserver.onCompleted()
    }
}