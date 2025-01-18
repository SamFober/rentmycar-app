package nl.avans.rentmycar.mock

import nl.avans.rentmycar.model.Review
import java.util.Date

object MockUtils {

    fun getMockReviews(): List<Review> {
        val reviews = listOf(
            Review(1, 1, "John Doe", 5, "Great car!", Date()),
            Review(2, 1, "Jane Smith", 4, "Nice ride.", Date()),
            Review(3, 2, "Bob Johnson", 5, "Excellent service!", Date())
        )
        println("MockUtils returning reviews: $reviews") // Debug log
        return reviews
    }
}
