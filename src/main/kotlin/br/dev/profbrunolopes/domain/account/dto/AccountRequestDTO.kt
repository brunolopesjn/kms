package br.dev.profbrunolopes.domain.account.dto

import io.micronaut.serde.annotation.Serdeable

@Serdeable.Deserializable
data class AccountRequestDTO(
    val username: String,
    val password: String,
    val role: String
)
