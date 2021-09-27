//package br.com.zup.pix.validation
//
//import br.com.zup.pix.model.enums.TipoChave
//import io.micronaut.core.annotation.AnnotationValue
//import io.micronaut.validation.validator.constraints.ConstraintValidator
//import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
//import jakarta.inject.Singleton
//
//
//@Singleton
//class ValidCPFValidator: ConstraintValidator<ValidCPF, TipoChave> {
//    override fun isValid(
//        value: TipoChave?,
//        annotationMetadata: AnnotationValue<ValidCPF>,
//        context: ConstraintValidatorContext
//    ): Boolean {
//        return true
//    }
//}