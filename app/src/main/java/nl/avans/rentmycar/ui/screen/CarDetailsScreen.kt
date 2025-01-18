package nl.avans.rentmycar.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import nl.avans.rentmycar.mock.MockUtils
import nl.avans.rentmycar.model.Review
import java.util.Date

@Composable
fun CarDetailsScreen(navController: NavController, carId: Int) {
    // Debugging logs added
    val allReviews = MockUtils.getMockReviews()
    println("All reviews: $allReviews")
    println("CarDetailsScreen received carId: $carId")

    // Improved filtering logic with debugging
    val reviews = remember(carId) {
        val filtered = allReviews.filter { it.carId == carId }
        println("Filtered reviews for carId $carId: $filtered")
        filtered
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Car Details", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Reviews", style = MaterialTheme.typography.headlineSmall)

        // Conditional rendering with LazyColumn
        if (reviews.isEmpty()) {
            Text("No reviews available.", style = MaterialTheme.typography.bodyMedium)
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(reviews) { review ->
                    ReviewItem(review = review)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("write_review/$carId") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Write a Review")
        }
    }
}


@Composable
fun ReviewItem(review: Review) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("${review.userName} ${review.rating} stars", style = MaterialTheme.typography.bodyMedium)
            Text(review.comment, style = MaterialTheme.typography.bodyMedium)
            Text(review.date.toString(), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CarDetailsScreenPreview() {
    val navController = rememberNavController()
    CarDetailsScreen(navController = navController, carId = 1)
}

@Preview(showBackground = true)
@Composable
fun ReviewItemPreview() {
    ReviewItem(
        review = Review(
            id = 1,
            carId = 1,
            userName = "John Doe",
            rating = 5,
            comment = "Great car!",
            date = Date()
        )
    )
}
