package nstu.ru.api.service

import nstu.ru.api.domain.ClientDomain
import nstu.ru.api.domain.HouseDomain
import nstu.ru.api.model.request.ClientRequest
import nstu.ru.api.model.request.HouseRequest
import nstu.ru.api.repository.UserRepository

class UserService(private val userRepository: UserRepository) {

    suspend fun findAllClients(): List<ClientDomain> =
        userRepository.findAllClients()
    suspend fun findAllHouses(): List<HouseDomain> =
        userRepository.findAllHouses()

    suspend fun getClientById(id: Int): ClientDomain =
        userRepository.getClientById(id)
    suspend fun getHouseById(id: Int): HouseDomain =
        userRepository.getHouseById(id)

    suspend fun insertClient(client: ClientRequest) =
        userRepository.insertClient(client)
    suspend fun insertHouse(house: HouseRequest) =
        userRepository.insertHouse(house)

    suspend fun deleteClient(id: Int) =
        userRepository.deleteClient(id)
    suspend fun deleteHouse(id: Int) =
        userRepository.deleteHouse(id)

    suspend fun updateClient(id: Int, client: ClientRequest) =
        userRepository.updateClient(id, client)
    suspend fun updateHouse(id: Int, house: HouseRequest) =
        userRepository.updateHouse(id, house)

}