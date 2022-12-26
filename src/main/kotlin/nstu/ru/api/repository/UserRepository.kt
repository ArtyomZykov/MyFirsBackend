package nstu.ru.api.repository


import kotlinx.coroutines.Dispatchers
import nstu.ru.api.domain.*
import nstu.ru.api.exception.UserNotFoundException
import nstu.ru.api.model.request.ClientRequest
import nstu.ru.api.model.request.HouseRequest
import nstu.ru.api.model.response.ApplicationUserResponse
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserRepository {

    @Throws(exceptionClasses = [UserNotFoundException::class])
    suspend fun findUserByUserNameAndPassword(userName: String, password: String): ApplicationUserDomain {
        val userPromise = suspendedTransactionAsync(context = Dispatchers.IO) {
            ApplicationUser.select {
                ApplicationUser.Name.eq(userName) and ApplicationUser.Password.eq(password)
            }.firstOrNull()?.let { ApplicationUser.fromRow(it) }
        }
        val appUser = userPromise.await()
        return appUser ?: throw UserNotFoundException()
    }

    @Throws(exceptionClasses = [UserNotFoundException::class])
    suspend fun findAllClients(): List<ClientDomain> {
        val userPromise = suspendedTransactionAsync(context = Dispatchers.IO) {
            Client.selectAll().map {
                Client.fromRow(it)
            }
        }
        val appUser = userPromise.await()
        return appUser ?: throw UserNotFoundException()
    }

    @Throws(exceptionClasses = [UserNotFoundException::class])
    suspend fun findAllHouses(): List<HouseDomain> {
        val userPromise = suspendedTransactionAsync(context = Dispatchers.IO) {
            House.selectAll().map {
                House.fromRow(it)
            }
        }
        val appUser = userPromise.await()
        return appUser ?: throw UserNotFoundException()
    }

    @Throws(exceptionClasses = [UserNotFoundException::class])
    suspend fun getClientById(id: Int): ClientDomain {
        val userPromise = suspendedTransactionAsync(context = Dispatchers.IO) {
            Client.select { Client.id eq id }.limit(1).single()
                .let { Client.fromRow(it) }
        }
        val appUser = userPromise.await()
        return appUser ?: throw UserNotFoundException()
    }

    @Throws(exceptionClasses = [UserNotFoundException::class])
    suspend fun getHouseById(id: Int): HouseDomain {
        val userPromise = suspendedTransactionAsync(context = Dispatchers.IO) {
            House.select { House.id eq id }.limit(1).single()
                .let { House.fromRow(it) }
        }
        val appUser = userPromise.await()
        return appUser ?: throw UserNotFoundException()
    }

    @Throws(exceptionClasses = [UserNotFoundException::class])
    suspend fun insertClient(client: ClientRequest) {
        val userPromise = suspendedTransactionAsync(context = Dispatchers.IO) {
            Client.insert{
                it[regNumber] = client.regNumber
                it[surname] = client.surname
                it[addressOutside] = client.addressOutside
                it[addressNumber] = client.addressNumber
                it[addressFlat] = client.addressFlat
                it[phone] = client.phone
            }
        }
    }

    @Throws(exceptionClasses = [UserNotFoundException::class])
    suspend fun insertHouse(house: HouseRequest) {
        val userPromise = suspendedTransactionAsync(context = Dispatchers.IO) {
            House.insert {
                it[storeys] = house.storeys
                it[material] = house.material
                it[dateConstr] = LocalDate.parse(house.dateConstr)
                it[dateLastOverhaul] = LocalDate.parse(house.dateLastOverhaul)
                it[isGarbageChute] = house.isGarbageChute
                it[isElevator] = house.isElevator
                it[isTelCable] = house.isTelCable
                it[area] = house.area
                it[floor] = house.floor
                it[rooms] = house.rooms
                it[totalArea] = house.totalArea
                it[livingArea] = house.livingArea
                it[kitchenArea] = house.kitchenArea
                it[isLoggia] = house.isLoggia
                it[isBalcony] = house.isBalcony
                it[addressOutside] = house.addressOutside
                it[addressNumber] = house.addressNumber
                it[addressFlat] = house.addressFlat
            }
        }
    }

    @Throws(exceptionClasses = [UserNotFoundException::class])
    suspend fun deleteClient(id: Int) {
        val userPromise = suspendedTransactionAsync(context = Dispatchers.IO) {
            Client.deleteWhere { Client.id eq id }
        }
    }

    @Throws(exceptionClasses = [UserNotFoundException::class])
    suspend fun deleteHouse(id: Int) {
        val userPromise = suspendedTransactionAsync(context = Dispatchers.IO) {
            House.deleteWhere { House.id eq id }
        }
    }

    @Throws(exceptionClasses = [UserNotFoundException::class])
    suspend fun updateClient(id: Int, client: ClientRequest) {
        val userPromise = suspendedTransactionAsync(context = Dispatchers.IO) {
            Client.update ({ Client.id eq id }) {
                it[regNumber] = client.regNumber
                it[surname] = client.surname
                it[addressOutside] = client.addressOutside
                it[addressNumber] = client.addressNumber
                it[addressFlat] = client.addressFlat
                it[phone] = client.phone
            }
        }
    }

    @Throws(exceptionClasses = [UserNotFoundException::class])
    suspend fun updateHouse(id: Int, house: HouseRequest) {
        val userPromise = suspendedTransactionAsync(context = Dispatchers.IO) {
            House.update ({ House.id eq id }) {
                it[storeys] = house.storeys
                it[material] = house.material
                it[dateConstr] = LocalDate.parse(house.dateConstr)
                it[dateLastOverhaul] = LocalDate.parse(house.dateLastOverhaul)
                it[isGarbageChute] = house.isGarbageChute
                it[isElevator] = house.isElevator
                it[isTelCable] = house.isTelCable
                it[area] = house.area
                it[floor] = house.floor
                it[rooms] = house.rooms
                it[totalArea] = house.totalArea
                it[livingArea] = house.livingArea
                it[kitchenArea] = house.kitchenArea
                it[isLoggia] = house.isLoggia
                it[isBalcony] = house.isBalcony
                it[addressOutside] = house.addressOutside
                it[addressNumber] = house.addressNumber
                it[addressFlat] = house.addressFlat
            }
        }
    }

    @Throws(exceptionClasses = [UserNotFoundException::class])
    suspend fun findUserByName(userName: String): ApplicationUserResponse {
        val userPromise = suspendedTransactionAsync(context = Dispatchers.IO) {
            (ApplicationUser innerJoin ApplicationFirm).slice(
                ApplicationUser.id, ApplicationUser.Name, ApplicationUser.Email,
                ApplicationUser.PhoneNumber, ApplicationUser.Status, ApplicationUser.FirmId,
                ApplicationFirm.Name, ApplicationFirm.Email, ApplicationFirm.PhoneNumber
            ).select {
                ApplicationUser.Name.eq(userName)
            }.firstOrNull()?.let {
                ApplicationUserResponse(
                    id = it[ApplicationUser.id].value,
                    name = it[ApplicationUser.Name],
                    email = it[ApplicationUser.Email],
                    phoneNumber = it[ApplicationUser.PhoneNumber],
                    status = it[ApplicationUser.Status],
                    firmId = it[ApplicationUser.FirmId]?.value,
                    firmName = it[ApplicationFirm.Name],
                    firmEmail = it[ApplicationFirm.Email],
                    firmPhoneNumber = it[ApplicationFirm.PhoneNumber]
                )
            }
        }
        val appUser = userPromise.await()
        return appUser ?: throw UserNotFoundException()
    }

    @Throws(exceptionClasses = [UserNotFoundException::class])
    suspend fun findUserByEmail(email: String): ApplicationUserDomain {
        val userPromise = suspendedTransactionAsync(context = Dispatchers.IO) {
            ApplicationUser.select { ApplicationUser.Email eq email }.firstOrNull()?.let {
                ApplicationUser.fromRow(it)
            }
        }
        val appUser = userPromise.await()
        return appUser ?: throw UserNotFoundException()
    }
}
