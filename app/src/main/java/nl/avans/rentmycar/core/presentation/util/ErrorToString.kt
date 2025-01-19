package nl.avans.rentmycar.core.presentation.util

import android.content.Context
import nl.avans.rentmycar.R
import nl.avans.rentmycar.auth.domain.login.LoginError
import nl.avans.rentmycar.core.domain.util.NetworkError

fun NetworkError.toString(context: Context): String {
    val resId = when (this) {
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        NetworkError.NO_INTERNET -> R.string.error_no_internet
        NetworkError.SERVER_ERROR -> R.string.error_unknown
        NetworkError.SERIALIZATION -> R.string.error_serialization
        NetworkError.UNKNOWN -> R.string.error_unknown
        NetworkError.ACCESS_DENIED -> R.string.error_access_denied
    }
    return context.getString(resId)
}

fun LoginError.toString(context: Context): String {
    val resId = when (this) {
        LoginError.INVALID_CREDENTIALS -> R.string.error_invalid_credentials
    }
    return context.getString(resId)
}