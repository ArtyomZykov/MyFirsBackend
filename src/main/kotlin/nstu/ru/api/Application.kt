package nstu.ru.api

import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.routing.*
import nstu.ru.api.controller.clientRoute
import nstu.ru.api.controller.houseRoute
import nstu.ru.api.plugins.configureSerialization
import nstu.ru.api.plugins.configureStatusPage
import nstu.ru.api.repository.UserRepository
import nstu.ru.api.service.DatabaseFactory
import nstu.ru.api.service.UserService

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the nstu.ru.api.main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    configureSerialization()
    configureStatusPage()

    val config = HoconApplicationConfig(ConfigFactory.load())
    DatabaseFactory(config).initialize()
    val userRepository = UserRepository()
    val userService = UserService(userRepository)
    routing {
        route("api") {
            clientRoute(userService)
            houseRoute(userService)
        }
    }
}
