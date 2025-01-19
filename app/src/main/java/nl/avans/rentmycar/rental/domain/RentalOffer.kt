package nl.avans.rentmycar.rental.domain

import java.util.UUID

data class RentalOffer(
    val id: UUID,
    val car: Car,
    val price: Double,
    val location: Location
)

data class Location(
    val lat: Double,
    val lon: Double
)