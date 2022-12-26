package nstu.ru.api.model.request

data class HouseRequest(
    var id: Int,
    var storeys: Int,
    var material: String,
    var dateConstr: String,
    var dateLastOverhaul: String,
    var isGarbageChute: Boolean,
    var isElevator: Boolean,
    var isTelCable: Boolean,
    var area: String,
    var floor: Int,
    var rooms: Int,
    var totalArea: Double,
    var livingArea: Double,
    var kitchenArea: Double,
    var isLoggia: Boolean,
    var isBalcony: Boolean,
    var addressOutside: String,
    var addressNumber: String,
    var addressFlat: Int,
)
