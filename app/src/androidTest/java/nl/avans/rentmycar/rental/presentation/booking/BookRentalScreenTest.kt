package nl.avans.rentmycar.rental.presentation.booking

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import org.junit.Rule
import org.junit.Test

class BookRentalScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun bookRentalScreen_showsUIElements() {
        composeTestRule.setContent {
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

        composeTestRule.onNodeWithText("Auto huren").assertIsDisplayed()
        composeTestRule.onNodeWithText("Kies een datum").assertIsDisplayed()
        composeTestRule.onNodeWithText("Kies een tijd").assertIsDisplayed()
        composeTestRule.onNodeWithText("Huren").assertIsDisplayed()
    }

    @Test
    fun bookRentalScreen_selectsDate() {
        var selectedDate: LocalDate? = null

        composeTestRule.setContent {
            BookRentalScreen(
                uiState = BookRentalUiState(
                    bookingDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                    bookingTime = LocalTime(12, 0)
                ),
                onDateChanged = { selectedDate = it },
                onTimeChanged = {},
                onSubmit = {}
            )
        }

        composeTestRule.onNodeWithText("Kies een datum").performClick()
        assert(selectedDate != null) { "Date selection failed" }
    }

    @Test
    fun bookRentalScreen_selectsTime() {
        var selectedTime: LocalTime? = null

        composeTestRule.setContent {
            BookRentalScreen(
                uiState = BookRentalUiState(
                    bookingDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                    bookingTime = LocalTime(12, 0)
                ),
                onDateChanged = {},
                onTimeChanged = { selectedTime = it },
                onSubmit = {}
            )
        }

        composeTestRule.onNodeWithText("Kies een tijd").performClick()
        assert(selectedTime != null) { "Time selection failed" }
    }

    @Test
    fun bookRentalScreen_submitBooking() {
        var bookingSubmitted = false

        composeTestRule.setContent {
            BookRentalScreen(
                uiState = BookRentalUiState(
                    bookingDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                    bookingTime = LocalTime(12, 0)
                ),
                onDateChanged = {},
                onTimeChanged = {},
                onSubmit = { bookingSubmitted = true }
            )
        }

        composeTestRule.onNodeWithText("Huren").performClick()
        assert(bookingSubmitted) { "Booking submission failed" }
    }
}
