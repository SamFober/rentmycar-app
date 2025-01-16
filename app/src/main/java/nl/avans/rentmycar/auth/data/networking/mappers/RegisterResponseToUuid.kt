package nl.avans.rentmycar.auth.data.networking.mappers

import nl.avans.rentmycar.auth.data.networking.dto.RegisterResponse
import java.util.UUID

fun RegisterResponse.toUuid(): UUID {
    return UUID.fromString(this.userId)
}