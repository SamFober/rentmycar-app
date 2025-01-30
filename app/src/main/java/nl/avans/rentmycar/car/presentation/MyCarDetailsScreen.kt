package nl.avans.rentmycar.car.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.avans.rentmycar.R
import nl.avans.rentmycar.ui.theme.RentMyCarTheme

@Composable
fun MyCarDetailsScreen(
    carId: Int,
    initialName: String = "Ford Ka",
    initialDescription: String = "Koekblik",
    image: Int = R.drawable.logo
) {
    val name = remember { mutableStateOf(initialName) }
    val description = remember { mutableStateOf(initialDescription) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Auto Details",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Details voor auto ID: $carId",
            style = MaterialTheme.typography.bodyMedium
        )
    }

    Column(
        modifier = Modifier.padding(top = 100.dp)
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
                text = "Naam:",
                style = MaterialTheme.typography.bodyLarge
            )
            BasicTextField(
                value = name.value,
                onValueChange = { name.value = it },
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "Beschrijving:",
                style = MaterialTheme.typography.bodyLarge
            )
            BasicTextField(
                value = description.value,
                onValueChange = { description.value = it },
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Save car details
        }) {
            Text("Opslaan")
        }
    }
}

@Preview
@Composable
private fun MyCarDetailsScreenPreview() {
    RentMyCarTheme {
        MyCarDetailsScreen(
            carId = 1,
            initialName = "Ford Ka",
            initialDescription = "Mooi koekblik",
            image = R.drawable.logo
        )
    }
}
