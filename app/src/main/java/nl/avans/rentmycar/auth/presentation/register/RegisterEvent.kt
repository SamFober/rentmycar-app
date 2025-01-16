package nl.avans.rentmycar.auth.presentation.register

import nl.avans.rentmycar.core.domain.util.Error

sealed interface RegisterEvent {
    object Success : RegisterEvent
    data class Failed(val error: Error) : RegisterEvent
}