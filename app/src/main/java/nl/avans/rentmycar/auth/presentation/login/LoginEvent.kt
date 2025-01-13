package nl.avans.rentmycar.auth.presentation.login

import nl.avans.rentmycar.auth.domain.LoginError

sealed interface LoginEvent {
    object Success : LoginEvent
    data class Error(val error: LoginError) : LoginEvent
}