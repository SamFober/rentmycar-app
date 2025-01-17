package nl.avans.rentmycar.auth.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponse(
    val refreshToken: String,
    val accessToken: String
)
