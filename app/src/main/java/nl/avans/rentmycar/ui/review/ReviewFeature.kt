package nl.avans.rentmycar.ui.review

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.avans.rentmycar.ui.review.components.ReviewForm
import nl.avans.rentmycar.ui.review.components.ReviewListScreen
import nl.avans.rentmycar.ui.review.models.Review

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewFeature(
    carId: String,
    navController: NavController
) {
    // State to hold the list of reviews
    val reviews = remember {
        mutableStateListOf<Review>(
            Review(
                carId = carId,
                userName = "John Doe",
                rating = 5,
                comment = "Amazing car! Super clean and comfortable.",
                timestamp = "2025-01-24T10:15:00"
            ),
            Review(
                carId = carId,
                userName = "Jane Smith",
                rating = 4,
                comment = "Good experience, but the tank was half full.",
                timestamp = "2025-01-23T18:00:00"
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reviews") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            // Review Form: Add a new review
            ReviewForm(
                carId = carId,
                onReviewSubmit = { review ->
                    reviews.add(review) // Add new review to the list
                }
            )

            // Divider between the form and the review list
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // Review List: Display all reviews for the current car
            ReviewListScreen(carId = carId, reviews = reviews)
        }
    }
}