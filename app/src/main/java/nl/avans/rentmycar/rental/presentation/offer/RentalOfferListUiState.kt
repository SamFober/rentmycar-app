package nl.avans.rentmycar.rental.presentation.offer

import nl.avans.rentmycar.rental.domain.RentalOffer

data class RentalOfferListUiState(
    val isLoading: Boolean = false,
    val rentalOffers: List<RentalOffer> = emptyList()
)
