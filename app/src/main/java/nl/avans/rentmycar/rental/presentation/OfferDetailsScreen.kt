package nl.avans.rentmycar.rental.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import nl.avans.rentmycar.R
import nl.avans.rentmycar.ui.theme.RentMyCarTheme


@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun OfferDetailsScreen(
    carId: String,
    name: String,
    description: String,
    imgUrl: String,
    onRentButtonClick: (carId: String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("RentMyCar") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Auto Details",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )
            GlideImage(
                model = imgUrl,
                contentDescription = "Car image",
                loading = placeholder(R.drawable.logo),
                failure = placeholder(R.drawable.logo),
                modifier = Modifier
                    .size(400.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier.padding(start = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Beschrijving: $description",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Column() {
                Button(onClick = {
                    onRentButtonClick(carId)
                }
                ) {
                    Text(text = "Huren")
                }
            }
        }
    }
}

@Preview
@Composable
private fun CarDetailsScreenPreview() {
    RentMyCarTheme {
        OfferDetailsScreen(
            carId = "",
            name = "Ford Ka",
            description = "Mooi koekblik",
            imgUrl = "",
            onRentButtonClick = {}
        )
    }
}