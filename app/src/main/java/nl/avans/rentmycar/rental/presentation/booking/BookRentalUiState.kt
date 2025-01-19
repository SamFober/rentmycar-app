package nl.avans.rentmycar.rental.presentation.booking

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

data class BookRentalUiState(
    val isLoading: Boolean = false,
    val bookingDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    val bookingTime: LocalTime = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()).time
)
