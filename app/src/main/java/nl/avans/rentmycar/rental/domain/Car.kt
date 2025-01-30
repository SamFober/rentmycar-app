package nl.avans.rentmycar.rental.domain

import java.util.UUID

data class Car(
    val carId: UUID,
    val brand: String,
    val model: String,
    val type: CarType,
    val seatCount: Int,
    val images: List<String>
)

enum class CarType {
    ICE,
    BEV,
    FCEV
}