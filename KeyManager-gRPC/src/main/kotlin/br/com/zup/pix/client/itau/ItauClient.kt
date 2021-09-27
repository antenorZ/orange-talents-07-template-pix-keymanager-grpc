package br.com.zup.pix.client.itau

import br.com.zup.pix.client.itau.dto.response.DadosClienteContaResponse
import br.com.zup.pix.client.itau.dto.response.DadosContaResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("\${itau.contas.url}")
interface ItauClient {

    @Get("/api/v1/clientes/{clientId}/contas{?tipo}")
    fun buscaContaPorTipo(@PathVariable clientId: String, @QueryValue tipo: String): HttpResponse<DadosContaResponse>

    @Get("/api/v1/clientes/{clientId}")
    fun buscaConta(@PathVariable clientId: String?): HttpResponse<DadosClienteContaResponse>
}