package nl.avans.rentmycar.auth.domain

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first
import nl.avans.rentmycar.core.domain.UserTokens

class TokenManager(private val dataStore: DataStore<UserTokens>) : ITokenManager {
    override suspend fun getAccessToken(): String {
        return dataStore.data.first().accessToken ?: ""
    }

    override suspend fun getRefreshToken(): String {
        return dataStore.data.first().refreshToken ?: ""
    }

    override suspend fun setTokens(accessToken: String, refreshToken: String) {
        dataStore.updateData {
            UserTokens(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        }
    }
}