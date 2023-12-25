package br.dev.profbrunolopes.domain.account

import br.dev.profbrunolopes.domain.account.dto.AccountRequestDTO
import br.dev.profbrunolopes.domain.account.dto.AccountResponseDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject
import kotlin.jvm.optionals.getOrElse

@Controller("/admin/accounts")
class AccountController(@Inject private val service: AccountService) {

    @Post
    fun createAccount(@Body accountRequestDTO: AccountRequestDTO): HttpResponse<AccountResponseDTO> {
        val account = Account(
            username = accountRequestDTO.username,
            password = accountRequestDTO.password,
            role = Role.valueOf(accountRequestDTO.role)
        )
        val createdAccountOptional = service.createAccount(account)
        val createdAccount = createdAccountOptional.getOrElse { return HttpResponse.serverError() }
        val accountResponseDTO = AccountResponseDTO(
            createdAccount.id!!,
            createdAccount.username,
            createdAccount.role.name
        )
        return HttpResponse.ok(accountResponseDTO)
    }

    @Get("/{id}")
    fun findAccountById(@PathVariable id: Long): HttpResponse<AccountResponseDTO> {
        val accountOptional = service.findAccountById(id)
        val account = accountOptional.getOrElse { return HttpResponse.notFound() }
        val accountResponseDTO = AccountResponseDTO(
            account.id!!,
            account.username,
            account.role.name,
        )
        return HttpResponse.ok(accountResponseDTO)
    }

    @Get
    fun findAllAccounts(): HttpResponse<List<AccountResponseDTO>> {
        val accountResponseDTOs =
            service.findAllAccounts().map { AccountResponseDTO(it.id!!, it.username, it.role.name) }
        return HttpResponse.ok(accountResponseDTOs)
    }

}