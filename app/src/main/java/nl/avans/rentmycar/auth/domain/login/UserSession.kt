package nl.avans.rentmycar.auth.domain.login

data class UserSession(
    val accessToken: String,
    val refreshToken: String
)
