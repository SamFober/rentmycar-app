package nl.avans.rentmycar.ui.review

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import nl.avans.rentmycar.ui.review.components.ReviewForm
import org.junit.Rule
import org.junit.Test

class ReviewFormTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testReviewFormSubmitButtonState() {
        composeTestRule.setContent {
            ReviewForm(carId = "car123", onReviewSubmit = {})
        }

        // Verify the Submit button is initially disabled
        composeTestRule.onNodeWithText("Submit").assertIsNotEnabled()

        // Simulate selecting a rating
        composeTestRule.onNodeWithContentDescription("Star 4").performClick()

        // Verify the Submit button is still disabled (no comment entered yet
        composeTestRule.onNodeWithText("Submit").assertIsNotEnabled()

        // Enter a comment
        composeTestRule.onNodeWithText("Comment").performTextInput("Great car!")

        // Verify the Submit button is now enabled
        composeTestRule.onNodeWithText("Submit").assertIsEnabled()

        // Simulate clicking the Submit button
        composeTestRule.onNodeWithText("Submit").performClick()

        // Verify that the form resets after submission
        composeTestRule.onNodeWithText("Submit").assertIsNotEnabled()
    }
}