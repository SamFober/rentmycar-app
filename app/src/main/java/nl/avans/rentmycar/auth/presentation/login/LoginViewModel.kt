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

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state

    private val _loginEventsChannel = Channel<LoginEvent>()
    val loginEvents = _loginEventsChannel.receiveAsFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.EmailChanged -> {
                _state.update {
                    it.copy(emailAddress = action.email)
                }
            }

            is LoginAction.PasswordChanged -> {
                _state.update {
                    it.copy(password = action.password)
                }
            }

            is LoginAction.Submit -> {
                submitCredentials()
            }
        }
    }

    private fun submitCredentials() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            val email = _state.value.emailAddress
            val password = _state.value.password

            delay(2000)

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