package nl.avans.rentmycar.ui.review.models

data class Review(
    val carId: String,
    val userName: String,
    val rating: Int,
    val comment: String,
    val timestamp: String
)
