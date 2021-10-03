package br.com.zup.pix.model.enums

import javax.persistence.EnumType
import javax.persistence.Enumerated


enum class TipoChave {
    CPF{
        override fun valida(chave: String?): Boolean {
            if(chave.isNullOrBlank()){
                return false
            }
            println("Chave CPF invalida")
          return chave.matches("^[0-9]{11}\$".toRegex())
        }

    },
    CELULAR{
        override fun valida(chave: String?): Boolean {
            if(chave.isNullOrBlank()){
                return false
            }
          return chave.matches("^\\+[1-9][0-9]\\d{1,14}\$".toRegex())
        }

    },
    EMAIL{
        override fun valida(chave: String?): Boolean {
            if(chave.isNullOrBlank()){
                return false
            }
            return chave.matches("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])".toRegex())
        }

    },
    ALEATORIA{
        override fun valida(chave: String?): Boolean {
            return true;
        }
    };

    abstract fun valida(chave: String?): Boolean
}