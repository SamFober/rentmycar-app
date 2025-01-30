package nl.avans.rentmycar.car.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import nl.avans.rentmycar.car.domain.Rental

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllRentals(
    platlist: List<Rental>,
    onDetailButtonPressed: (Int) -> Unit,
    onAddCarButtonPressed: () -> Unit
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
            Modifier
                .fillMaxSize()
                .padding(paddingValues), // Apply padding here
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                "My Cars",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(top = 25.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // Use weight to take remaining space
                contentPadding = PaddingValues(16.dp)
            ) {
                items(platlist) {
                    MyCarCard(
                        it.name,
                        it.description,
                        it.imageRes,
                        it.id,
                        onDetailButtonPressed = onDetailButtonPressed
                    )
                }
            }
        }
        Column(
            Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ) {
            AddCarButton(onClick = onAddCarButtonPressed)
        }
    }
}

@Composable
fun AddCarButton(onClick: () -> Unit) {
    SmallFloatingActionButton(
        onClick = { onClick() },
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.secondary
    ) {
        Icon(Icons.Filled.Add, "Add car")
    }
}

@Serializable
object StartScreen

@Serializable
data class DetailScreen(
    val carId: Int
)