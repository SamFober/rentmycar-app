package nl.avans.rentmycar.rental.data.networking.dto.booking

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class AddRentalBookingRequest(
    val rentalOfferId: String,
    val dateStart: LocalDateTime,
    val dateEnd: LocalDateTime
)
