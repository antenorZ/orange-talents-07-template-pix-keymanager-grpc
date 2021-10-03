package br.com.zup.pix.validation

import br.com.zup.pix.model.dto.NovaChavePixDTO
import br.com.zup.pix.model.enums.TipoChave
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import jakarta.inject.Singleton

@Singleton
class ValidPixKeyValidator: ConstraintValidator<ValidPixKey, NovaChavePixDTO> {

    override fun isValid(
        value: NovaChavePixDTO?,
        annotationMetadata: AnnotationValue<ValidPixKey>,
        context: ConstraintValidatorContext
    ): Boolean {
        if(value?.tipo == null){
            return false
        }

        when(value.tipo){
            TipoChave.CPF -> return value.chave!!.matches("^[0-9]{11}\$".toRegex())

            TipoChave.EMAIL -> return value.chave!!.matches("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])".toRegex())

            TipoChave.CELULAR -> return value.chave!!.matches("^\\+[1-9][0-9]\\d{1,14}\$".toRegex())

            TipoChave.ALEATORIA -> return true
        }

//        return value.tipo.valida(value.chave)
    }

}