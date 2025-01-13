package nl.avans.rentmycar.auth.presentation.login

data class LoginState(
    val isLoading: Boolean = false,
    val emailAddress: String = "",
    val password: String = ""
)