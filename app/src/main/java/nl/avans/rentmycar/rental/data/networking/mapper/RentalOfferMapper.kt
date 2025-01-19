package nl.avans.rentmycar.rental.data.networking.mapper

import nl.avans.rentmycar.rental.data.networking.dto.offer.RentalOfferResponse
import nl.avans.rentmycar.rental.domain.Location
import nl.avans.rentmycar.rental.domain.RentalOffer
import java.util.UUID

fun RentalOfferResponse.toRentalOffer(): RentalOffer {
    return RentalOffer(
        id = UUID.fromString(this.offerId),
        car = this.car.toCar(),
        price = this.price,
        location = Location(this.location.latitude, this.location.longitude)
    )
}