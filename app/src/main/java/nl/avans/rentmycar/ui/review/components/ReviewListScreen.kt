package nl.avans.rentmycar.ui.review.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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

@Composable
fun SortDropdownMenu(
    selectedOption: String,
    onSortOptionChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Newest", "Oldest", "Highest Rating")

    Box(modifier = Modifier.padding(16.dp)) {
        // Dropdown button
        Button(onClick = { expanded = true }) {
            Text(text = selectedOption)
        }

        // Dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onSortOptionChange(option)
                        expanded = false
                    },
                    text = {
                        Text(
                            text = option,
                            style = if (option == selectedOption) MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            ) else MaterialTheme.typography.bodyMedium
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun ReviewCard(
    review: Review
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "By: ${review.userName}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            // Display review details
            Text(
                text = "${review.rating} ★",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp),
                color = MaterialTheme.colorScheme.primary
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))

            Text(
                text = review.comment,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Date: ${review.timestamp}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}