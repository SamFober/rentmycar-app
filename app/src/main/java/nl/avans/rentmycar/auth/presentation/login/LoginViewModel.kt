package nl.avans.rentmycar.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.avans.rentmycar.auth.domain.IAuthDataSource
import nl.avans.rentmycar.auth.domain.ITokenManager
import nl.avans.rentmycar.auth.domain.login.LoginError
import nl.avans.rentmycar.core.domain.util.NetworkError
import nl.avans.rentmycar.core.domain.util.onError
import nl.avans.rentmycar.core.domain.util.onSuccess

class LoginViewModel(
    private val authDataSource: IAuthDataSource,
    private val tokenManager: ITokenManager
) : ViewModel() {
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
            println(tokenManager.getRefreshToken())
            _state.update {
                it.copy(isLoading = true)
            }
            val email = _state.value.emailAddress
            val password = _state.value.password

            authDataSource.login(email, password)
                .onSuccess { session ->
                    _state.update {
                        it.copy(isLoading = false)
                    }
                    tokenManager.setTokens(
                        accessToken = session.accessToken,
                        refreshToken = session.refreshToken
                    )
                    _loginEventsChannel.send(LoginEvent.Success)
                }
                .onError { error ->
                    _state.update {
                        it.copy(isLoading = false)
                    }
                    if (error == NetworkError.ACCESS_DENIED) {
                        _loginEventsChannel.send(LoginEvent.Failed(LoginError.INVALID_CREDENTIALS))
                    } else {
                        _loginEventsChannel.send(LoginEvent.Failed(error))
                    }
                }
        }
    }
}