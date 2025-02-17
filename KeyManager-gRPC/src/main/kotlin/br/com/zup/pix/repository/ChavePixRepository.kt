package br.com.zup.pix.repository

import br.com.zup.pix.model.ChavePix
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface ChavePixRepository: JpaRepository<ChavePix, Long>{
    fun existsByChave(chave: String?): Boolean
    fun findByChaveAndClientId(chave: String?, clienteId: String?): Optional<ChavePix>

    fun findByPixId(pixId: String?): Optional<ChavePix>

    fun findByChave(chave: String?): Optional<ChavePix>

    fun findAllByClientId(clienteId: String?): List<ChavePix>
}