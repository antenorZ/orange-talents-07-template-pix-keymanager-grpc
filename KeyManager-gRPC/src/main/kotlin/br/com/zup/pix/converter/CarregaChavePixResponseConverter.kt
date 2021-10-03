package br.com.zup.pix.converter

import br.com.zup.CarregaChavePixResponse
import br.com.zup.pix.model.ChavePix
import br.com.zup.pix.model.enums.TipoChave
import br.com.zup.pix.model.enums.TipoConta

class CarregaChavePixResponseConverter {

    fun convert(chavePix: ChavePix): CarregaChavePixResponse{
        return CarregaChavePixResponse.newBuilder()
            .setClientId(chavePix.clientId ?: "")
            .setPixId(chavePix.pixId)
            .setChave(CarregaChavePixResponse.ChavePix
                .newBuilder()
                .setTipo(br.com.zup.TipoChave.valueOf(chavePix.tipo!!.name))
                .setChave(chavePix.chave)
                .setConta(CarregaChavePixResponse.ChavePix.ContaInfo.newBuilder()
                    .setTipo(br.com.zup.TipoConta.valueOf(chavePix.tipoConta.name))
                    .setInstituicao(chavePix.conta.instituicao)
                    .setNomeTitular(chavePix.conta.nomeTitular)
                    .setCpfTitular(chavePix.conta.cpfTitular)
                    .setAgencia(chavePix.conta.agencia)
                    .setNumeroConta(chavePix.conta.numeroConta)
                    .build()
                )
            ).build()
    }
}