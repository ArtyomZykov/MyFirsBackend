package nstu.ru.api.model.response

data class TokenResponse(val accessToken: String, val createdAt: Long, val expireInMs: Long)