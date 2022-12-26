package nstu.ru.api.domain

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.javatime.date
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class HouseDomain(
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

object House : IntIdTable("house") {
    var storeys: Column<Int> = integer("storeys")
    var material: Column<String> = text("material")
    var dateConstr: Column<LocalDate> = date("date_construction")
    var dateLastOverhaul: Column<LocalDate> = date("date_last_overhaul")
    var isGarbageChute: Column<Boolean> = bool("is_garbage_chute")
    var isElevator: Column<Boolean> = bool("is_elevator")
    var isTelCable: Column<Boolean> = bool("is_tel_cable")
    var area: Column<String> = text("area")
    var floor: Column<Int> = integer("floor")
    var rooms: Column<Int> = integer("rooms")
    var totalArea: Column<Double> = double("total_area")
    var livingArea: Column<Double> = double("living_area")
    var kitchenArea: Column<Double> = double("kitchen_area")
    var isLoggia: Column<Boolean> = bool("is_loggia")
    var isBalcony: Column<Boolean> = bool("is_balcony")
    var addressOutside: Column<String> = text("address_outside")
    var addressNumber: Column<String> = text("address_number")
    var addressFlat: Column<Int> = integer("address_flat")
}

fun House.fromRow(resultRow: ResultRow): HouseDomain {
    val formatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val dateConstr = resultRow[dateConstr].format(formatter)
    val dateLastOverhaul = resultRow[dateLastOverhaul].format(formatter)

    return HouseDomain(
        id = resultRow[id].value,
        storeys = resultRow[storeys],
        material = resultRow[material],
        dateConstr = dateConstr,
        dateLastOverhaul = dateLastOverhaul,
        isGarbageChute = resultRow[isGarbageChute],
        isElevator = resultRow[isElevator],
        isTelCable = resultRow[isTelCable],
        area = resultRow[area],
        floor = resultRow[floor],
        rooms = resultRow[rooms],
        totalArea = resultRow[totalArea],
        livingArea = resultRow[livingArea],
        kitchenArea = resultRow[kitchenArea],
        isLoggia = resultRow[isLoggia],
        isBalcony = resultRow[isBalcony],
        addressOutside = resultRow[addressOutside],
        addressNumber = resultRow[addressNumber],
        addressFlat = resultRow[addressFlat]
    )
}