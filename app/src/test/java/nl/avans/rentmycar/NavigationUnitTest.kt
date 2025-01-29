import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationUnitTest {

    @Composable
    @Test
    fun testNavigationFromRentalListToDetails() {
        // Context verkrijgen
        val context = ApplicationProvider.getApplicationContext<Context>()
        val navController = createNavController(context)

        // Stel startroute in
        navController.navigate("rentalList")
        assertEquals("rentalList", navController.currentDestination?.route)

        // Simuleer navigatie naar carDetails
        val carId = 1
        navController.navigate("carDetails/$carId")
        assertEquals("carDetails/{carId}", navController.currentDestination?.route)
    }

    // Hulpmethode om een NavController te maken
    @Composable
    private fun createNavController(context: Context): NavController {
        return rememberNavController().apply {
            setGraph(
                NavGraphBuilder.createGraph(
                    startDestination = "rentalList",
                    builder = {
                        composable("rentalList") {}
                        composable("carDetails/{carId}") {}
                    }
                )
            )
        }
    }
}
