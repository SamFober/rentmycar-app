package nl.avans.rentmycar.model

import java.util.Date

data class Review(
    val id: Int,
    val carId: Int,
    val userName: String,
    val rating: Int,
    val comment: String,
    val date: Date
)
