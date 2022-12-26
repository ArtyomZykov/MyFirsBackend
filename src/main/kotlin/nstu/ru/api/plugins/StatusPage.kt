package nstu.ru.api.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import nstu.ru.api.exception.AuthenticationException
import nstu.ru.api.exception.AuthorizationException
import nstu.ru.api.exception.TokenCreationException
import nstu.ru.api.exception.UserNotFoundException

fun Application.configureStatusPage() {
    install(StatusPages) {
        exception<AuthenticationException> { call, _ ->
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<AuthorizationException> { call, _ ->
            call.respond(HttpStatusCode.Forbidden)
        }
        exception<UserNotFoundException> { call, _ ->
            call.respond(HttpStatusCode.NotFound)
        }
        exception<TokenCreationException> { call, _ ->
            call.respond(HttpStatusCode.InternalServerError)
        }
    }
}
