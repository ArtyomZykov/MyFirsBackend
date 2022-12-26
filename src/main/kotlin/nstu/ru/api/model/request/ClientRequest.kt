package nstu.ru.api.model.request

data class ClientRequest(
    var id: Int,
    var regNumber: Int,
    var surname: String,
    var addressOutside: String,
    var addressNumber: String,
    var addressFlat: Int,
    var phone: String
)
