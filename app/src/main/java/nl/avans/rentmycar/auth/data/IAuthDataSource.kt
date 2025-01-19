package nl.avans.rentmycar.auth.data

import kotlinx.datetime.LocalDate
import nl.avans.rentmycar.auth.domain.login.UserSession
import nl.avans.rentmycar.core.domain.util.Error
import nl.avans.rentmycar.core.domain.util.Result

interface IAuthDataSource {
    suspend fun login(emailAddress: String, password: String): Result<UserSession, Error>
    suspend fun register(
        firstName: String,
        lastName: String,
        dateOfBirth: LocalDate,
        emailAddress: String,
        password: String
    ): Result<String, Error>
}