package nstu.ru.api.controller

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import nstu.ru.api.service.UserService

fun Route.userRoute(userService: UserService) {

    authenticate("jwt-token") {
        route("/user") {
            get {
                val principal = call.principal<JWTPrincipal>()
                val username = principal!!.payload.getClaim("name").asString()
                val appUser = userService.findUserByName(username)
                call.respond(message = appUser, status = HttpStatusCode.OK)
            }
        }
    }
}
