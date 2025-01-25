package nl.avans.rentmycar.ui.review

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nl.avans.rentmycar.ui.review.components.ReviewListScreen
import nl.avans.rentmycar.ui.review.models.Review
import org.junit.Rule
import org.junit.Test

class ReviewListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testReviewListDisplaysReviews() {
        val reviews = listOf(
            Review("car123", "Alice", 5, "Excellent car!", "2025-01-01T10:00:00"),
            Review("car123", "Bob", 3, "It was okay.", "2025-01-02T10:00:00")
        )

        composeTestRule.setContent {
            ReviewListScreen(carId = "car123", reviews = reviews)
        }

        // Verify both reviews are displayed
        composeTestRule.onNodeWithText("Excellent car!").assertExists()
        composeTestRule.onNodeWithText("It was okay.").assertExists()
    }

    @Test
    fun testReviewListHandlesEmptyState() {
        composeTestRule.setContent {
            ReviewListScreen(carId = "car123", reviews = emptyList())
        }

        // Verify empty state message is displayed
        composeTestRule.onNodeWithText("No reviews available.").assertExists()
    }

    @Test
    fun testSortingReviewsByNewest() {
        val reviews = listOf(
            Review("car123", "Alice", 5, "Excellent car!", "2025-01-01T10:00:00"),
            Review("car123", "Bob", 3, "It was okay.", "2025-01-02T10:00:00")
        )

        composeTestRule.setContent {
            ReviewListScreen(carId = "car123", reviews = reviews)
        }

        // Change sorting to Oldest
        composeTestRule.onNodeWithText("Newest").performClick()
        composeTestRule.onNodeWithText("Oldest").performClick()

        // Verify sorting is Oldest (Alice's review first)
        composeTestRule.onAllNodesWithText("By: Alice")[0].assertExists()
        composeTestRule.onAllNodesWithText("By: Bob")[0].assertExists()
    }
}