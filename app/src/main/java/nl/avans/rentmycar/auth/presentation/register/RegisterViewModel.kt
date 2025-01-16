package nl.avans.rentmycar.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import nl.avans.rentmycar.auth.domain.AuthDataSource
import nl.avans.rentmycar.core.domain.util.onError
import nl.avans.rentmycar.core.domain.util.onSuccess

class RegisterViewModel(
    private val authDataSource: AuthDataSource
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterUiState())
    val state = _state

    private val _registerEvents = Channel<RegisterEvent>()
    val registerEvents = _registerEvents.receiveAsFlow()

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
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            authDataSource.register(
                firstName = _state.value.firstName,
                lastName = _state.value.lastName,
                dateOfBirth = _state.value.dateOfBirth,
                emailAddress = _state.value.emailAddress,
                password = _state.value.password
            ).onSuccess {
                _state.update {
                    it.copy(isLoading = false)
                }
                _registerEvents.send(RegisterEvent.Success)
            }.onError { error ->
                _state.update {
                    it.copy(isLoading = false)
                }
                _registerEvents.send(RegisterEvent.Failed(error))
            }
        }
    }
}