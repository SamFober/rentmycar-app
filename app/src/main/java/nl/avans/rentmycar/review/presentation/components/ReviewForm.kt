package nl.avans.rentmycar.ui.review.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import nl.avans.rentmycar.review.domain.Review

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


@Preview(showBackground = true)
@Composable
fun ReviewFormPreview() {
    ReviewForm(carId = "car123", onReviewSubmit = {})
}