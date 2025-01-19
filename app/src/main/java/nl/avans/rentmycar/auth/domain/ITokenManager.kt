package nl.avans.rentmycar.auth.domain

interface ITokenManager {
    suspend fun getAccessToken(): String
    suspend fun getRefreshToken(): String
    suspend fun setTokens(accessToken: String, refreshToken: String)
}