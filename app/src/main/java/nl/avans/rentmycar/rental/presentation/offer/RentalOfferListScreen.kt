package nl.avans.rentmycar.rental.presentation.offer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nl.avans.rentmycar.rental.presentation.components.RentalCard
import nl.avans.rentmycar.ui.theme.RentMyCarTheme
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@Composable
fun RentalOfferListScreenRoute(
    viewModel: RentalOfferListViewModel = koinViewModel(),
    onDetailButtonPressed: (
        carId: UUID,
        name: String,
        description: String,
        imgRes: String
    ) -> Unit,
    onCarButtonPressed: () -> Unit,
    onReviewButtonPressed: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    RentalOfferListScreen(
        uiState = state,
        onDetailButtonPressed = onDetailButtonPressed,
        onCarButtonPressed = onCarButtonPressed,
        onReviewButtonPressed = onReviewButtonPressed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentalOfferListScreen(
    uiState: RentalOfferListUiState,
    onDetailButtonPressed: (
        carId: UUID,
        name: String,
        description: String,
        imgRes: String
    ) -> Unit,
    onCarButtonPressed: () -> Unit,
    onReviewButtonPressed: () -> Unit
) {
    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(uiState.rentalOffers) {
                RentalCard(
                    name = "${it.car.brand} ${it.car.model}",
                    description = "${it.car.seatCount} zitplaatsen",
                    imgUrl = it.car.images[0],
                    offerId = it.id,
                    onDetailButtonPressed = onDetailButtonPressed
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(5.dp),
        ) {
            Button(
                onClick = {
                    onCarButtonPressed()
                }
            ) {
                Text("Car")
            }
            Button(
                onClick = {
                    onReviewButtonPressed()
                }
            ) {
                Text("Review")
            }
        }
    }
}

@Preview
@Composable
fun RentalOfferListScreenPreview() {
    RentMyCarTheme {
        RentalOfferListScreen(
            uiState = RentalOfferListUiState(rentalOffers = rentalOfferLists),
            onDetailButtonPressed = { carId, name, description, imgRes ->

            },
            onCarButtonPressed = {},
            onReviewButtonPressed = {}
        )
    }
}



