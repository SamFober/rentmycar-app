package nl.avans.rentmycar.rental.data.networking.dto.booking

import kotlinx.serialization.Serializable

@Serializable
data class AddRentalBookingResponse(
    val bookingId: String
)
