package br.dev.profbrunolopes.domain.account

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.MappedProperty
import java.time.Instant

@MappedEntity("accounts")
data class Account(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val username: String,
    val password: String,
    val role: Role,
    @DateCreated
    @MappedProperty("created_at")
    val createdAt: Instant? = null,
    @DateUpdated
    @MappedProperty("updated_at")
    val updatedAt: Instant? = null
)
