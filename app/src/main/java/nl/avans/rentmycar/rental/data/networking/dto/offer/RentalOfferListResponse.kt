package nl.avans.rentmycar.rental.data.networking.dto.offer

import kotlinx.serialization.Serializable

@Serializable
data class RentalOfferListResponse(
    val rentalOffers: List<RentalOfferResponse>
)
