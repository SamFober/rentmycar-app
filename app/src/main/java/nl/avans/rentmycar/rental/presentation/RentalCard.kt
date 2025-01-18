package nl.avans.rentmycar.rental.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import nl.avans.rentmycar.R
import nl.avans.rentmycar.ui.theme.RentMyCarTheme

@Composable
fun RentalCard(
    name: String,
    description: String,
    image: Int,
    carId: Int,
    onDetailButtonPressed: (
        carId: Int,
        name: String,
        description: String,
        imgRes: Int) -> Unit
) {
    val navController = rememberNavController()
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Car Image",
                modifier = Modifier
                    .size(130.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit
            )
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge
                )
                Button(onClick = {
                    onDetailButtonPressed(carId, name, description, image)
                }
                ) {
                    Text(text = "Details")
                }
            }
        }
    }
}

@Preview
@Composable
private fun RentalCardPreview() {
    RentMyCarTheme {
        RentalCard(
            name = "Ford Ka",
            description = "Mooi koekblik",
            image = R.drawable.logo,
            carId = 1,
            onDetailButtonPressed = { carId, name, description, imgRes ->  }
        )
    }
}