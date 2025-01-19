package nl.avans.rentmycar.rental.presentation.booking

import nl.avans.rentmycar.core.domain.util.NetworkError

sealed interface RentalBookingEvent {
    object Success : RentalBookingEvent
    data class Error(val error: NetworkError) : RentalBookingEvent
}