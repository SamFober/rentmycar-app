package nl.avans.rentmycar.auth.presentation.login

import nl.avans.rentmycar.core.domain.util.Error

sealed interface LoginEvent {
    object Success : LoginEvent
    data class Failed(val error: Error) : LoginEvent
}