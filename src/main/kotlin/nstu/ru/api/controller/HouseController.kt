package nstu.ru.api.controller

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import nstu.ru.api.model.request.HouseRequest
import nstu.ru.api.service.UserService

fun Route.houseRoute(userService: UserService) {

    route("/houses") {
        get {
            val id = call.parameters["id"]
            if (id.isNullOrEmpty()) {
                val appUser = userService.findAllHouses()
                call.respond(message = appUser, status = HttpStatusCode.OK)
                return@get
            }
            val appUser = userService.getHouseById(id.toInt())
            call.respond(message = appUser, status = HttpStatusCode.OK)
        }
        post {
            val request = call.receive<HouseRequest>()
            userService.insertHouse(request)
            call.respond(message = "", status = HttpStatusCode.OK)
        }
        put {
            val id = call.parameters["id"]
            if (id.isNullOrEmpty()) {
                call.respond(
                    message = "Id not found!",
                    status = HttpStatusCode.NotFound
                )
                return@put
            }
            val request = call.receive<HouseRequest>()
            userService.updateHouse(id.toInt(), request)
            call.respond(message = "", status = HttpStatusCode.OK)
        }
        delete {
            val id = call.parameters["id"]
            if (id.isNullOrEmpty()) {
                call.respond(
                    message = "Id not found!",
                    status = HttpStatusCode.NotFound
                )
                return@delete
            }
            userService.deleteHouse(id.toInt())
            call.respond(message = "", status = HttpStatusCode.OK)
        }
    }
}
