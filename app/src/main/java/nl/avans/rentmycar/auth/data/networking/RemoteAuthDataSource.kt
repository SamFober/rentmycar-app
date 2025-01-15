package nl.avans.rentmycar.auth.data.networking

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import nl.avans.rentmycar.auth.data.networking.dto.LoginRequest
import nl.avans.rentmycar.auth.data.networking.dto.LoginResponse
import nl.avans.rentmycar.auth.domain.AuthDataSource
import nl.avans.rentmycar.auth.domain.login.UserSession
import nl.avans.rentmycar.core.data.networking.constructUrl
import nl.avans.rentmycar.core.data.networking.safeCall
import nl.avans.rentmycar.core.domain.util.Error
import nl.avans.rentmycar.core.domain.util.Result
import nl.avans.rentmycar.core.domain.util.map

class RemoteAuthDataSource(
    private val httpClient: HttpClient
) : AuthDataSource {
    override suspend fun login(
        emailAddress: String,
        password: String
    ): Result<UserSession, Error> {
        return safeCall<LoginResponse> {
            httpClient.post(
                urlString = constructUrl("/auth/login")
            ) {
                setBody(LoginRequest(email = emailAddress, password = password))
            }
        }.map { response ->
            UserSession(accessToken = response.accessToken, refreshToken = response.refreshToken)
        }
    }

}