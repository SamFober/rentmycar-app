package nl.avans.rentmycar.rental.data.networking.mapper

import nl.avans.rentmycar.rental.domain.Car
import nl.avans.rentmycar.rental.data.networking.dto.offer.CarResponse
import java.util.UUID

fun CarResponse.toCar(): Car {
    return Car(
        carId = UUID.fromString(this.carId),
        brand = this.brand,
        model = this.model,
        type = this.type,
        seatCount = this.seatCount,
        images = this.imageUrls
    )
}