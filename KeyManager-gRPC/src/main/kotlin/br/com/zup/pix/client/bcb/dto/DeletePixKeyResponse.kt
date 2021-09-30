package br.com.zup.pix.client.bcb.dto

import java.time.LocalDateTime

data class DeletePixKeyResponse(
    val key: String?,
    val participant: String?,
    val deletedAt: LocalDateTime
){

}