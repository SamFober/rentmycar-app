package nl.avans.rentmycar.auth.data.networking

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.datetime.LocalDate
import nl.avans.rentmycar.auth.data.IAuthDataSource
import nl.avans.rentmycar.auth.data.networking.dto.login.LoginRequest
import nl.avans.rentmycar.auth.data.networking.dto.login.LoginResponse
import nl.avans.rentmycar.auth.data.networking.dto.register.RegisterRequest
import nl.avans.rentmycar.auth.data.networking.dto.register.RegisterResponse
import nl.avans.rentmycar.auth.domain.login.UserSession
import nl.avans.rentmycar.core.data.networking.constructUrl
import nl.avans.rentmycar.core.data.networking.safeCall
import nl.avans.rentmycar.core.domain.util.Error
import nl.avans.rentmycar.core.domain.util.Result
import nl.avans.rentmycar.core.domain.util.map

class RemoteAuthDataSource(
    private val httpClient: HttpClient
) : IAuthDataSource {
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

    override suspend fun register(
        firstName: String,
        lastName: String,
        dateOfBirth: LocalDate,
        emailAddress: String,
        password: String
    ): Result<String, Error> {
        return safeCall<RegisterResponse> {
            httpClient.post(
                urlString = constructUrl("/auth/register")
            ) {
                setBody(
                    RegisterRequest(
                        firstName = firstName,
                        lastName = lastName,
                        dateOfBirth = dateOfBirth,
                        emailAddress = emailAddress,
                        password = password
                    )
                )
            }
        }.map { response ->
            response.userId
        }
    }

}