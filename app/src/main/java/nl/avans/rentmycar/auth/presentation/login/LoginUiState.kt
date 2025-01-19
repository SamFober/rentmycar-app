package nl.avans.rentmycar.auth.presentation.login

data class LoginUiState(
    val isLoading: Boolean = false,
    val emailAddress: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false
)