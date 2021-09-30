package br.com.zup.pix.client.bcb.model.enums

import br.com.zup.pix.model.enums.TipoChave

enum class PixKeyType(val domainType: TipoChave?){
    CPF(TipoChave.CPF),
    CNPJ(null),
    PHONE(TipoChave.CELULAR),
    EMAIL(TipoChave.EMAIL),
    RANDOM(TipoChave.ALEATORIA);

    companion object {
        private val mapping = PixKeyType.values().associateBy(PixKeyType::domainType)

        fun by(domainType: TipoChave?): PixKeyType{
            return mapping[domainType] ?: throw IllegalArgumentException("PixKeyType invalida")
        }
    }

}
