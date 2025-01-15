package nl.avans.rentmycar.auth.domain

import nl.avans.rentmycar.auth.domain.login.UserSession
import nl.avans.rentmycar.core.domain.util.Error
import nl.avans.rentmycar.core.domain.util.Result

interface AuthDataSource {
    suspend fun login(emailAddress: String, password: String): Result<UserSession, Error>
}