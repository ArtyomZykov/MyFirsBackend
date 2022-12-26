package nstu.ru.api.domain

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow

data class ClientDomain(
    var id: Int,
    var regNumber: Int,
    var surname: String,
    var addressOutside: String,
    var addressNumber: String,
    var addressFlat: Int,
    var phone: String,
)

object Client : IntIdTable("client") {
    var regNumber: Column<Int> = integer("registration_number")
    var surname: Column<String> = text("surname")
    var addressOutside: Column<String> = text("address_outside")
    var addressNumber: Column<String> = text("address_number")
    var addressFlat: Column<Int> = integer("address_flat")
    var phone: Column<String> = text("phone")
}

fun Client.fromRow(resultRow: ResultRow): ClientDomain =
    ClientDomain(
        id = resultRow[id].value,
        regNumber = resultRow[regNumber],
        surname = resultRow[surname],
        addressOutside = resultRow[addressOutside],
        addressNumber = resultRow[addressNumber],
        addressFlat = resultRow[addressFlat],
        phone = resultRow[phone]
    )
