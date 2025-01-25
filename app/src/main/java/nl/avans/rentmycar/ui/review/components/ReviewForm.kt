package nl.avans.rentmycar.ui.review.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.avans.rentmycar.ui.review.models.Review

@Composable
fun ReviewForm(
    carId: String,
    onReviewSubmit: (Review) -> Unit
) {
    var rating by remember { mutableIntStateOf(0) }
    var comment by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Leave a Review",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Rating Bar
        RatingBar(
            rating = rating,
            onRatingChange = { newRating -> rating = newRating }
        )

        // Comment Input Field
        OutlinedTextField(
            value = comment,
            onValueChange = { comment = it },
            label = { Text("Comment") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            isError = comment.isBlank() && rating > 0 // Optional: Visual feedback
        )

        // Submit Button
        Button(
            onClick = {
                val timestamp = java.time.LocalDateTime.now().toString()
                val review = Review(
                    carId = carId,
                    userName = "Anonymous", // Replace with user data if available
                    rating = rating,
                    comment = comment,
                    timestamp = timestamp
                )
                onReviewSubmit(review)

                // Reset form fields after submission
                rating = 0
                comment = ""
            },
            enabled = rating > 0 && comment.isNotBlank(), // Ensure both rating and comment are valid
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

@Composable
fun RatingBar(
    rating: Int,
    onRatingChange: (Int) -> Unit
) {
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
        for (i in 1..5) {
            Icon(
                imageVector = if (i <= rating) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = null,
                tint = if (i <= rating) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier
                    .size(32.dp)
                    .padding(horizontal = 4.dp)
                    .clickable { onRatingChange(i) }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ReviewFormPreview() {
    ReviewForm(carId = "car123", onReviewSubmit = {})
}