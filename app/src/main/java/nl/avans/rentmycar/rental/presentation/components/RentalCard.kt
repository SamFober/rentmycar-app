package nl.avans.rentmycar.rental.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import java.util.UUID

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RentalCard(
    name: String,
    description: String,
    imgUrl: String,
    offerId: UUID,
    onDetailButtonPressed: (
        carId: UUID,
        name: String,
        description: String,
        imgUrl: String
    ) -> Unit
) {
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
            GlideImage(
                model = imgUrl,
                contentDescription = "Car image",
                loading = placeholder(R.drawable.logo),
                failure = placeholder(R.drawable.logo),
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
                    onDetailButtonPressed(offerId, name, description, imgUrl)
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
            imgUrl = "https://abstoragev4.blob.core.windows.net/auctions/43258/large/43258-1a_ex.JPG",
            offerId = UUID.randomUUID(),
            onDetailButtonPressed = { carId, name, description, imgRes -> }
        )
    }
}