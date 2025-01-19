package nl.avans.rentmycar.rental.data.networking.dto.offer

import kotlinx.serialization.Serializable
import nl.avans.rentmycar.rental.domain.CarType

@Serializable
data class CarResponse(
    val carId: String,
    val brand: String,
    val model: String,
    val type: CarType,
    val seatCount: Int,
    val imageUrls: List<String>
)
