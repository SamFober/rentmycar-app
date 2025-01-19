package nl.avans.rentmycar.rental.presentation.booking

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import nl.avans.rentmycar.BookRentalScreen
import nl.avans.rentmycar.core.domain.util.onError
import nl.avans.rentmycar.core.domain.util.onSuccess
import nl.avans.rentmycar.rental.data.IRentalDataSource
import nl.avans.rentmycar.rental.domain.RentalBooking
import java.util.UUID

class BookRentalViewModel(
    private val rentalDataSource: IRentalDataSource,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val bookRental = savedStateHandle.toRoute<BookRentalScreen>()

    private val _state = MutableStateFlow(BookRentalUiState())
    val state = _state

    private val _events = Channel<RentalBookingEvent>()
    val events = _events.receiveAsFlow()

    fun bookingDateChanged(date: LocalDate) {
        _state.update {
            it.copy(
                bookingDate = date
            )
        }
    }

    fun updateBookingTime(time: LocalTime) {
        _state.update {
            it.copy(
                bookingTime = time
            )
        }
    }

    fun makeBooking() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            rentalDataSource.addRentalBooking(
                RentalBooking(
                    rentalOfferId = UUID.fromString(bookRental.offerId),
                    dateStart = LocalDateTime(_state.value.bookingDate, _state.value.bookingTime),
                    dateEnd = LocalDateTime(2026, 12, 31, 23, 59)
                )
            ).onSuccess {
                _state.update {
                    it.copy(isLoading = false)
                }
                _events.send(RentalBookingEvent.Success)
            }.onError { error ->
                _events.send(RentalBookingEvent.Error(error))
            }
        }
    }
}