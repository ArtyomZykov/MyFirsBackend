package nstu.ru.api.model.response

import nstu.ru.api.domain.enum.Status

data class ApplicationUserResponse(
    val id: Int, val name: String,
    val email: String, val phoneNumber: String,
    val status: Status, val firmId: Int?,
    val firmName: String, val firmEmail: String,
    val firmPhoneNumber: String
)