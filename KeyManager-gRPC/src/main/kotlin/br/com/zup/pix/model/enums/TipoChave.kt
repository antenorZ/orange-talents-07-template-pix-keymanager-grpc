package br.com.zup.pix.model.enums

enum class TipoChave {
    CPF{
        override fun valida(chave: String?): Boolean {
            return true;
        }

    },
    CELULAR{
        override fun valida(chave: String?): Boolean {
            return true;
        }

    },
    EMAIL{
        override fun valida(chave: String?): Boolean {
            return true;
        }

    },
    ALEATORIA{
        override fun valida(chave: String?): Boolean {
            return true;
        }
    };

    abstract fun valida(chave: String?): Boolean
}