package nl.avans.rentmycar.rental.data.networking.dto.offer

import kotlinx.serialization.Serializable

@Serializable
data class RentalOfferResponse(
    val offerId: String,
    val car: CarResponse,
    val price: Double,
    val location: LocationResponse
)

@Serializable
data class LocationResponse(
    val longitude: Double,
    val latitude: Double
)
