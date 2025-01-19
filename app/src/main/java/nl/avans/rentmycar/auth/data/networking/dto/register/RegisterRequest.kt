package nl.avans.rentmycar.auth.data.networking.dto.register

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate,
    val emailAddress: String,
    val password: String
)
