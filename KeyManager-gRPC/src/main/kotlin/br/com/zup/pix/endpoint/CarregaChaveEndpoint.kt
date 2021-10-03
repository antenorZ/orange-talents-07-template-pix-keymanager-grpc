package br.com.zup.pix.endpoint

import br.com.zup.CarregaChavePixRequest
import br.com.zup.CarregaChavePixResponse
import br.com.zup.KeyManagerCarregaGRPCServiceGrpc
import br.com.zup.pix.client.bcb.BcbClient
import br.com.zup.pix.converter.CarregaChavePixResponseConverter
import br.com.zup.pix.extensions.toModel
import br.com.zup.pix.repository.ChavePixRepository
import io.grpc.stub.StreamObserver
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class CarregaChaveEndpoint(
    @Inject private val repository: ChavePixRepository,
    @Inject private val bcbClient: BcbClient):
    KeyManagerCarregaGRPCServiceGrpc.KeyManagerCarregaGRPCServiceImplBase()
{
    override fun carrega(request: CarregaChavePixRequest, responseObserver: StreamObserver<CarregaChavePixResponse>) {
        val filtro = request.toModel()

        val chavePix = filtro.filtra(repository = repository, bcbClient = bcbClient)

        responseObserver.onNext(CarregaChavePixResponseConverter().convert(chavePix))

        responseObserver.onCompleted()
    }
}