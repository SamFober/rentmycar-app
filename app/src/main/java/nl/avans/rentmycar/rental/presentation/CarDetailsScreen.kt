package nl.avans.rentmycar.rental.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.avans.rentmycar.R
import nl.avans.rentmycar.ui.theme.RentMyCarTheme


@Composable
fun CarDetailsScreen(carId: Int, name: String, description: String, image: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Car Details",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        Text(
            text = "Details voor auto ID: $carId",
            style = MaterialTheme.typography.bodyMedium
        )
    }
    Column(
        modifier = Modifier
            .padding(top = 100.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .size(400.dp)
                .padding(8.dp),
            contentScale = ContentScale.Fit
        )
        Column(Modifier.padding(start = 8.dp)) {
            Text(
                text = name,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Beschrijving: $description"
            )
        }
    }
}

@Preview
@Composable
private fun CarDetailsScreenPreview() {
    RentMyCarTheme { CarDetailsScreen(carId = 1, name = "Ford Ka", description = "Mooi koekblik", image = R.drawable.logo) }
}