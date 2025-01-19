package nl.avans.rentmycar.rental.data.networking

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import nl.avans.rentmycar.core.data.networking.constructUrl
import nl.avans.rentmycar.core.data.networking.safeCall
import nl.avans.rentmycar.core.domain.util.NetworkError
import nl.avans.rentmycar.core.domain.util.Result
import nl.avans.rentmycar.core.domain.util.map
import nl.avans.rentmycar.rental.data.IRentalDataSource
import nl.avans.rentmycar.rental.data.networking.dto.booking.AddRentalBookingRequest
import nl.avans.rentmycar.rental.data.networking.dto.booking.AddRentalBookingResponse
import nl.avans.rentmycar.rental.data.networking.dto.offer.RentalOfferListResponse
import nl.avans.rentmycar.rental.data.networking.mapper.toRentalOffer
import nl.avans.rentmycar.rental.domain.RentalBooking
import nl.avans.rentmycar.rental.domain.RentalOffer
import java.util.UUID

class RemoteRentalDataSource(
    private val httpClient: HttpClient
) : IRentalDataSource {
    override suspend fun getAllRentalOffers(): Result<List<RentalOffer>, NetworkError> {
        return safeCall<RentalOfferListResponse> {
            httpClient.get(
                urlString = constructUrl("/rental/offer")
            )
        }.map { response ->
            response.rentalOffers.map { it.toRentalOffer() }
        }
    }

    override suspend fun addRentalBooking(booking: RentalBooking): Result<UUID, NetworkError> {
        return safeCall<AddRentalBookingResponse> {
            httpClient.post(
                urlString = constructUrl("/rental/booking")
            ) {
                setBody(
                    AddRentalBookingRequest(
                        rentalOfferId = booking.rentalOfferId!!.toString(),
                        dateStart = booking.dateStart,
                        dateEnd = booking.dateEnd
                    )
                )
            }
        }.map { response ->
            UUID.fromString(response.bookingId.toString())
        }
    }
}
