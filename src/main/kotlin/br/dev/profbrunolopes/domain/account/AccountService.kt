package br.dev.profbrunolopes.domain.account

import br.dev.profbrunolopes.common.exceptions.DuplicateRecordException
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.Optional

@Singleton
class AccountService(@Inject private val repository: AccountRepository) {

    fun createAccount(account: Account): Optional<Account> {
        if (repository.existsByUsername(account.username)) {
            throw DuplicateRecordException("There is an account with same username")
        }
        val newAccount = repository.save(account)
        return Optional.of(newAccount)
    }

    fun findAccountById(id: Long): Optional<Account> = repository.findById(id)

    fun findAllAccounts(): List<Account> = repository.findAll()

}