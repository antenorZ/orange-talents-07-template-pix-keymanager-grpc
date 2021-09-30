package br.com.zup.pix.client.bcb.model.enums

import br.com.zup.pix.model.enums.TipoConta

enum class AccountType{
    CACC, SVGS;

    companion object{
        fun by(domainType: TipoConta): AccountType{
            return when (domainType){
                TipoConta.CONTA_CORRENTE -> CACC
                TipoConta.CONTA_POUPANCA -> SVGS
            }
        }
    }
}