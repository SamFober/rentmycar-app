package nl.avans.rentmycar.core.data.networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import nl.avans.rentmycar.auth.data.networking.dto.RefreshTokenRequest
import nl.avans.rentmycar.auth.data.networking.dto.RefreshTokenResponse
import nl.avans.rentmycar.auth.domain.ITokenManager

object HttpClientFactory {
    fun create(engine: HttpClientEngine, tokenManager: ITokenManager): HttpClient {
        return HttpClient(engine) {
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.ANDROID
            }
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(
                            accessToken = tokenManager.getAccessToken(),
                            refreshToken = tokenManager.getRefreshToken()
                        )
                    }
                    refreshTokens {
                        val token = client.get {
                            markAsRefreshTokenRequest()
                            url(constructUrl("/auth/refresh-token"))
                            setBody(RefreshTokenRequest(refreshToken = ""))
                        }.body<RefreshTokenResponse>()
                        tokenManager.setTokens(
                            accessToken = token.accessToken,
                            refreshToken = token.refreshToken
                        )
                        BearerTokens(
                            accessToken = token.accessToken,
                            refreshToken = token.refreshToken
                        )
                    }
                }
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
    }
}