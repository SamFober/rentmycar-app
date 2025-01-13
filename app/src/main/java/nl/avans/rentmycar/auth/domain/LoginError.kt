package nl.avans.rentmycar.auth.domain

import nl.avans.rentmycar.core.domain.util.Error

enum class LoginError : Error {
    INVALID_CREDENTIALS
}