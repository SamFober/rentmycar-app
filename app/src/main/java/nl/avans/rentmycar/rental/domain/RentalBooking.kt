package nl.avans.rentmycar.rental.domain

import kotlinx.datetime.LocalDateTime
import java.util.UUID

data class RentalBooking(
    val bookingId: UUID? = null,
    val rentalOfferId: UUID? = null,
    val dateStart: LocalDateTime,
    val dateEnd: LocalDateTime
)
