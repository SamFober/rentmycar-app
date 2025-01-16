package nl.avans.rentmycar.auth.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val userId: String
)
