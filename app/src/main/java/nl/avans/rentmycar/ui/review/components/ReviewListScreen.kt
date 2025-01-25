package nl.avans.rentmycar.ui.review.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.avans.rentmycar.ui.review.models.Review


@Composable
fun ReviewListScreen(
    carId: String,
    reviews: List<Review>
) {
    // State for sorting option
    var sortOption by remember { mutableStateOf("Newest") }

    // Sort reviews based on the selected option
    val sortedReviews = when (sortOption) {
        "Newest" -> reviews.sortedByDescending { it.timestamp }
        "Oldest" -> reviews.sortedBy { it.timestamp }
        "Highest Rating" -> reviews.sortedByDescending { it.rating }
        else ->reviews
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Dropdown menu for sorting options
        SortDropdownMenu(
            selectedOption = sortOption,
            onSortOptionChange = { sortOption = it }
        )

        // Review list
        if (sortedReviews.isEmpty()) {
            Text(
                text = "No reviews available.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn(
                modifier =Modifier
                    .fillMaxSize()
                    .animateContentSize()
            ) {
                items(sortedReviews.size) { review ->
                    ReviewCard(review = sortedReviews[review])
                }
            }
        }
    }
}