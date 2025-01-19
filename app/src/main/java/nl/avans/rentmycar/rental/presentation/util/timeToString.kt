package nl.avans.rentmycar.rental.presentation.util

import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.char

fun LocalTime.toFormattedString(): String {
    val format = LocalTime.Format {
        hour()
        char(':')
        minute()
    }
    return format.format(this)
}