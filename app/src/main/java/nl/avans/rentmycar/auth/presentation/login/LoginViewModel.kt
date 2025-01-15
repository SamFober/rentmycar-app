package nl.avans.rentmycar.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.avans.rentmycar.auth.domain.LoginError

class LoginViewModel() : ViewModel() {
    private val _state = MutableStateFlow(LoginUiState())
    val state = _state

    private val _loginEventsChannel = Channel<LoginEvent>()
    val loginEvents = _loginEventsChannel.receiveAsFlow()

    fun updateEmail(email: String) {
        _state.update {
            it.copy(emailAddress = email)
        }
    }

    fun updatePassword(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }

    fun updatePasswordVisibility(visible: Boolean) {
        _state.update {
            it.copy(passwordVisible = visible)
        }
    }

    fun submit() {
        login()
    }

    private fun login() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            val email = _state.value.emailAddress
            val password = _state.value.password

            delay(2000)

            //TODO: Verify credentials on backend

            if (email == "test@gmail.com" && password == "password") {
                _state.update { it.copy(isLoading = false) }
                _loginEventsChannel.send(LoginEvent.Success)
            } else {
                _state.update { it.copy(isLoading = false) }
                _loginEventsChannel.send(LoginEvent.Error(LoginError.INVALID_CREDENTIALS))
            }
        }
    }
}