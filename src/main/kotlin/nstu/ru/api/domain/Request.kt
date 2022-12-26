package nstu.ru.api.domain

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select

data class RequestDomain(
    var id: Int,
    var client: ClientDomain,
    var house: HouseDomain,
    var buyOrSale: String,
    var price: Int,
    var termsTrans: String
)

object Request : IntIdTable("request") {
    var client_id = reference("client_id", Client)
    var house_id = reference("house_id", House)
    var buyOrSale: Column<String> = text("bue_sale")
    var price: Column<Int> = integer("price")
    var termsTrans: Column<String> = text("terms_transaction")
}

fun Request.fromRow(resultRow: ResultRow): RequestDomain =
    RequestDomain(
        id = resultRow[id].value,
        client = resultRow[client_id].value.let{
            Client.select { Client.id eq it }.limit(1).single().let { Client.fromRow(it) }
        },
        house = resultRow[house_id].value.let{
            House.select { Client.id eq it }.limit(1).single().let { House.fromRow(it) }
        },
        buyOrSale = resultRow[buyOrSale],
        price = resultRow[price],
        termsTrans = resultRow[termsTrans],
    )

