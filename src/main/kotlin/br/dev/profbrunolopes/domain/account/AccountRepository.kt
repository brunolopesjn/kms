package br.dev.profbrunolopes.domain.account

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import java.util.Optional

@JdbcRepository(dialect = Dialect.POSTGRES)
interface AccountRepository : CrudRepository<Account, Long> {

    fun existsByUsername(username: String): Boolean
    fun findByUsername(username: String): Optional<Account>
}