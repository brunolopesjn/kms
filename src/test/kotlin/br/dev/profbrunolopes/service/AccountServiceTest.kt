package br.dev.profbrunolopes.service

import br.dev.profbrunolopes.common.exceptions.DuplicateRecordException
import br.dev.profbrunolopes.domain.account.Account
import br.dev.profbrunolopes.domain.account.AccountService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.longs.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import jakarta.inject.Inject

@MicronautTest
class AccountServiceTest(
    @Inject private val service: AccountService,
) : BehaviorSpec({

    given("Dado o serviço AccountService") {
        `when`("Quando o método createAccount for executado com uma instância válida de Account") {
            then( "Então os dados devem ser inseridos no banco de dados e retornado uma instância de Account com os dados recém inseridos") {

                val account = Account(
                    username = "johnjoe",
                    password = "abc123",
                    role = Role.ADMIN
                )

                val result = service.createAccount(account).get()

                result.id shouldNotBe null
                result.id?.shouldBeGreaterThanOrEqual(1L)
                result.username shouldBe account.username
                result.password shouldBe account.password
                result.role shouldBe account.role
                result.createdAt shouldNotBe null
                result.updatedAt shouldNotBe null

            }
        }
        `when`("Quando o método createAccount for executado com uma instância válida de Account mas com o username já cadastrado") {
            then( "Então deve ser lançada uma exceção") {

                val firstAccount = Account(
                    username = "elvis",
                    password = "abc123",
                    role = Role.ADMIN
                )
                service.createAccount(firstAccount)

                val duplicatedAccount = Account(
                    username = "elvis",
                    password = "123abc",
                    role = Role.MANAGER
                )
                val exception = shouldThrow<DuplicateRecordException> { service.createAccount(duplicatedAccount) }
                exception.message shouldBe "There is an account with same username"

            }
        }
    }

})