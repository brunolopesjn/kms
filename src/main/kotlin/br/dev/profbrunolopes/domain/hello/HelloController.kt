package br.dev.profbrunolopes.domain.hello

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable

@Controller("/hello")
class HelloController {

    @Get
    fun sayHelloWorld(): String {
        return "Hello world!"
    }

    @Get("/{name}")
    fun sayHello(@PathVariable name: String): String {
        return "Hello $name!"
    }

}