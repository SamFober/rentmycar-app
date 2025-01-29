package nl.avans.rentmycar.rental.presentation.booking

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalTime
import kotlinx.datetime.todayIn
import nl.avans.rentmycar.R
import nl.avans.rentmycar.core.presentation.util.ObserveAsEvents
import nl.avans.rentmycar.rental.presentation.util.toFormattedString
import nl.avans.rentmycar.ui.theme.RentMyCarTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookRentalScreenRoute(
    viewModel: BookRentalViewModel = koinViewModel(),
    onBookingAdded: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    ObserveAsEvents(events = viewModel.events) { event ->
        when (event) {
            is RentalBookingEvent.Success -> {
                Toast.makeText(
                    context,
                    "Boeking geplaatst",
                    Toast.LENGTH_LONG
                ).show()
                onBookingAdded()
            }

            is RentalBookingEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    BookRentalScreen(
        uiState = state,
        onDateChanged = {
            viewModel.bookingDateChanged(it)
        },
        onTimeChanged = {
            viewModel.updateBookingTime(it)
        },
        onSubmit = {
            viewModel.makeBooking()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookRentalScreen(
    uiState: BookRentalUiState,
    onDateChanged: (LocalDate) -> Unit,
    onTimeChanged: (LocalTime) -> Unit,
    onSubmit: () -> Unit
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
        val dateDialogState = rememberMaterialDialogState()
        val timeDialogState = rememberMaterialDialogState()
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.rent_car),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Row() {
                Column(
                    modifier = Modifier
                        .padding(end = 32.dp)
                ) {
                    Button(onClick = {
                        dateDialogState.show()
                    }) {
                        Text(text = stringResource(id = R.string.choose_date))
                    }
                    Text(text = uiState.bookingDate.toFormattedString())
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    Button(onClick = {
                        timeDialogState.show()
                    }) {
                        Text(text = stringResource(id = R.string.choose_time))
                    }
                    Text(text = uiState.bookingTime.toFormattedString())
                }
            }
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Text(text = stringResource(id = R.string.click_car_rent))
            Button(onClick = {
                onSubmit()
            }
            ) {
                Text(text = stringResource(id = R.string.rent))
            }
        }
        MaterialDialog(
            dialogState = dateDialogState,
            buttons = {
                positiveButton(text = "OK")
                negativeButton(text = "Cancel")
            }
        ) {
            datepicker(
                initialDate = uiState.bookingDate.toJavaLocalDate(),
                title = stringResource(id = R.string.choose_date)
            ) {
                onDateChanged(LocalDate(it.year, it.month, it.dayOfMonth))
            }
        }
        MaterialDialog(
            dialogState = timeDialogState,
            buttons = {
                positiveButton(text = "OK")
                negativeButton(text = "Cancel")
            }
        ) {
            timepicker(
                initialTime = uiState.bookingTime.toJavaLocalTime(),
                title = stringResource(id = R.string.choose_time)
            ) {
                onTimeChanged(LocalTime(it.hour, it.minute))
            }
        }
    }
}

@Preview
@Composable
private fun RentalScreenPreview() {
    RentMyCarTheme {
        BookRentalScreen(
            uiState = BookRentalUiState(
                bookingDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                bookingTime = LocalTime(12, 0)
            ),
            onDateChanged = {},
            onTimeChanged = {},
            onSubmit = {}
        )
    }
}