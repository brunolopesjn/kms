package br.dev.profbrunolopes.domain.account.dto

import io.micronaut.serde.annotation.Serdeable

@Serdeable.Serializable
data class AccountResponseDTO(
    val id: Long,
    val username: String,
    val role: String
)
