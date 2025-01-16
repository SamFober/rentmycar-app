package nl.avans.rentmycar.auth.presentation.register

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn


data class RegisterUiState(
    val isLoading: Boolean = false,
    val registrationSuccessful: Boolean = false,
    val firstName: String = "",
    val lastName: String = "",
    val dateOfBirth: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    val emailAddress: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false
)
