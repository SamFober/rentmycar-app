package nl.avans.rentmycar.auth.presentation.login

sealed interface LoginAction {
    data class EmailChanged(val email: String) : LoginAction
    data class PasswordChanged(val password: String) : LoginAction
    object Submit : LoginAction
}