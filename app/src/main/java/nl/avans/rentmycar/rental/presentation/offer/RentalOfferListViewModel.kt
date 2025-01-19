package nl.avans.rentmycar.rental.presentation.offer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.avans.rentmycar.core.domain.util.onError
import nl.avans.rentmycar.core.domain.util.onSuccess
import nl.avans.rentmycar.rental.data.networking.RemoteRentalDataSource

class RentalOfferListViewModel(
    private val rentalDataSource: RemoteRentalDataSource
) : ViewModel() {
    private val _state = MutableStateFlow(RentalOfferListUiState())
    val state = _state
        .onStart { loadRentalOffers() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed((5000L)),
            RentalOfferListUiState()
        )

    private val _events = Channel<RentalListEvent>()
    val events = _events.receiveAsFlow()

    fun loadRentalOffers() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            rentalDataSource
                .getAllRentalOffers()
                .onSuccess { rentalOffers ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            rentalOffers = rentalOffers
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(RentalListEvent.Error(error))
                }
        }
    }
}