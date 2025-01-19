package nl.avans.rentmycar.rental.data

import nl.avans.rentmycar.core.domain.util.NetworkError
import nl.avans.rentmycar.core.domain.util.Result
import nl.avans.rentmycar.rental.domain.RentalBooking
import nl.avans.rentmycar.rental.domain.RentalOffer
import java.util.UUID

interface IRentalDataSource {
    suspend fun getAllRentalOffers(): Result<List<RentalOffer>, NetworkError>
    suspend fun addRentalBooking(booking: RentalBooking): Result<UUID, NetworkError>
}