import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nl.avans.rentmycar.rental.presentation.CarDetailsScreen
import org.junit.Rule
import org.junit.Test

class RentalListUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRentalCardClickNavigatesToDetails() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "rentalList") {
                composable("rentalList") {
                    RentalList(navController = navController)
                }
                composable("carDetails/{carId}") { backStackEntry ->
                    val carId = backStackEntry.arguments?.getString("carId")
                    CarDetailsScreen(carId = carId ?: "")
                }
            }
        }

        // Controleer dat een kaart met tekst wordt weergegeven
        composeTestRule.onNodeWithText("Car Name 1").assertIsDisplayed()

        // Klik op de kaart
        composeTestRule.onNodeWithText("Car Name 1").performClick()

        // Controleer dat navigatie naar het detailscherm is gebeurd
        composeTestRule.onNodeWithText("Details for Car 1").assertIsDisplayed()
    }
}
