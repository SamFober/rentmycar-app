package nl.avans.rentmycar.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import nl.avans.rentmycar.model.Review
import java.util.Date

@Composable
fun WriteReviewScreen(navController: NavController, carId: Int) {

    var userName by remember { mutableStateOf(TextFieldValue("")) }
    var rating by remember { mutableIntStateOf(0) }
    var comment by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(48.dp)
    ) {
        Text(text = "Write a Review", style = MaterialTheme.typography.headlineMedium)

        TextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("User Name") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = comment,
            onValueChange = { comment = it },
            label = { Text("Comment")},
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            (1..5).forEach { star ->
                IconButton(onClick = { rating = star }) {
                    Icon(
                        imageVector = if (star <= rating) Icons.Filled.Star else Icons.Outlined.Star,
                        contentDescription = null
                    )
                }
            }
        }

        Button(
            onClick = {
                val review = Review(
                    id = 0, // Temporary ID,
                    carId = carId,
                    userName = userName.text,
                    rating = rating,
                    comment = comment.text,
                    date = Date()
                )
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Submit")
        }
    }
}

@Preview(showBackground=true)
@Composable
fun WriteReviewScreenPreview() {
    val navController = rememberNavController()
    WriteReviewScreen(navController = navController, carId = 1)
}



