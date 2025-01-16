package nl.avans.rentmycar.auth.presentation.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate

class RegisterViewModel() : ViewModel() {
    private val _state = MutableStateFlow(RegisterUiState())
    val state = _state

    fun updateFirstName(firstName: String) {
        _state.update {
            it.copy(firstName = firstName)
        }
    }

    fun updateLastName(lastName: String) {
        _state.update {
            it.copy(lastName = lastName)
        }
    }

    fun updateDateOfBirth(dateOfBirth: LocalDate) {
        _state.update {
            it.copy(dateOfBirth = dateOfBirth)
        }
    }

    fun updateEmailAddress(emailAddress: String) {
        _state.update {
            it.copy(emailAddress = emailAddress)
        }
    }

    fun updatePassword(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }

    fun updatePasswordVisibility(isVisible: Boolean) {
        _state.update {
            it.copy(passwordVisible = isVisible)
        }
    }

    fun register() {

    }
}