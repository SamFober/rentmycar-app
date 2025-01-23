package nl.avans.rentmycar.rental.presentation.offer

import nl.avans.rentmycar.car.domain.Car
import nl.avans.rentmycar.car.domain.CarType
import nl.avans.rentmycar.rental.domain.Location
import nl.avans.rentmycar.rental.domain.RentalOffer
import java.util.UUID

val rentalOfferLists = listOf(
    RentalOffer(
        id = UUID.randomUUID(),
        car = Car(
            carId = UUID.randomUUID(),
            brand = "Ford",
            model = "Ka",
            type = CarType.ICE,
            seatCount = 4,
            images = listOf("https://abstoragev4.blob.core.windows.net/auctions/43258/large/43258-1a_ex.JPG")
        ),
        price = 50.0,
        location = Location(lat = 51.58465292999375, lon = 4.797563316654039)
    ),
    RentalOffer(
        id = UUID.randomUUID(),
        car = Car(
            carId = UUID.randomUUID(),
            brand = "Dacia",
            model = "Duster",
            type = CarType.ICE,
            seatCount = 6,
            images = listOf("https://abstoragev4.blob.core.windows.net/auctions/43258/large/43258-1a_ex.JPG")
        ),
        price = 30.0,
        location = Location(lat = 51.58465292999375, lon = 4.797563316654039)
    )
)