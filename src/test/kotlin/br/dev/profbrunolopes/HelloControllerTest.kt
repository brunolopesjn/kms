package br.dev.profbrunolopes

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import jakarta.inject.Inject

@MicronautTest
class HelloControllerTest(
    @Inject @Client("/") private val client: HttpClient
) : BehaviorSpec({

    given("Dado o controlador HelloController") {
        `when`("Quando uma requisição ao endpoint GET /hello ocorre"){
            then("Então uma string Hello world! é enviada como resposta"){
                val response = client.toBlocking().retrieve("/hello")
                response shouldBe "Hello world!"
            }
        }
        `when`("Quando uma requisição ao endpont GET /hello/John ocorre"){
            then("Então uma string Hello John! é enviada como resposta") {
                val response = client.toBlocking().retrieve("/hello/John")
                response shouldBe "Hello John!"
            }
        }
    }

})