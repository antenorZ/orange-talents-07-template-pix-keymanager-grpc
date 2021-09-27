//package br.com.zup.pix.validation
//
//import javax.validation.Constraint
//import javax.validation.Payload
//import javax.validation.ReportAsSingleViolation
//import javax.validation.constraints.Pattern
//import kotlin.reflect.KClass
//
////@ReportAsSingleViolation
//@Constraint(validatedBy = [])
//@Pattern(regexp = "/^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}\$/")
//@Retention(AnnotationRetention.RUNTIME)
//@Target(AnnotationTarget.FIELD, AnnotationTarget.CONSTRUCTOR, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
//annotation class ValidCPF(
//    val message: String = "Não é um formato válido de CPF",
//    val groups: Array<KClass<Any>> = [],
//    val payload: Array<KClass<Payload>> = [],
//)
