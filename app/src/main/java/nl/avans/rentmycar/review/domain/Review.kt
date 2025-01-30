package nl.avans.rentmycar.review.domain

data class Review(
    val carId: String,
    val userName: String,
    val rating: Int,
    val comment: String,
    val timestamp: String
)

