package nl.avans.rentmycar.auth.presentation.register

import kotlinx.datetime.LocalDate


data class RegisterUiState(
    val isLoading: Boolean = false,
    val registrationSuccessful: Boolean = false,
    val firstName: String = "",
    val lastName: String = "",
    val dateOfBirth: LocalDate = LocalDate(1998, 11, 27),
    val emailAddress: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false
)
