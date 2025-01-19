package nl.avans.rentmycar.rental.presentation.offer

import nl.avans.rentmycar.core.domain.util.NetworkError

sealed interface RentalListEvent {
    data class Error(val error: NetworkError) : RentalListEvent
}