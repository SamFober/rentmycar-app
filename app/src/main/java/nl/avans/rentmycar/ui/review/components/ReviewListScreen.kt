package nl.avans.rentmycar.ui.review.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.avans.rentmycar.ui.review.models.Review

@Composable
fun ReviewListScreen(
    carId: String,
    reviews: List<Review>
) {
    // Filter reviews by carId
    val carReviews = reviews.filter { it.carId == carId }

    Column(modifier = Modifier.fillMaxSize()) {
        // Title card
        Text(
            text = "Reviews",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        if (carReviews.isEmpty()) {
            // Show a message if no reviews are available
            Text(
                text = "No reviews available.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        } else {
            // Display the list of reviews
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(carReviews.size) { review ->
                    ReviewCard(review = carReviews[review])
                }
            }
        }
    }
}

@Composable
fun ReviewCard(review: Review) {
    // Implement the UI for a single review card
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Display review details
            Text(
                text = "${review.rating}★",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = review.comment,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "By ${review.userName}",
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = "Date: ${review.timestamp}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewListScreenPreview() {
    val sampleReviews = listOf(
        Review(
            carId = "car123",
            userName = "John Doe",
            rating = 5,
            comment = "Great car, very clean and smooth ride!",
            timestamp = "2025-01-24T15:30:00"
        ),
        Review(
            carId = "car123",
            userName = "Jane Smith",
            rating = 4,
            comment = "Good car, but the fuel tank was half empty.",
            timestamp = "2025-01-23T10:15:00"
        )
    )

    ReviewListScreen(carId = "car123", reviews = sampleReviews)
}
