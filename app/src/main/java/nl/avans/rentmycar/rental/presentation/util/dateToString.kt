package nl.avans.rentmycar.rental.presentation.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.char

fun LocalDate.toFormattedString(): String {
    val format = LocalDate.Format {
        dayOfMonth()
        char('-')
        monthNumber()
        char('-')
        year()
    }
    return format.format(this)
}