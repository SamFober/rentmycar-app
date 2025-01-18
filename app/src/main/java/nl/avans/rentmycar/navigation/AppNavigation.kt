package nl.avans.rentmycar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.createGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import nl.avans.rentmycar.ui.screen.CarDetailsScreen
import nl.avans.rentmycar.ui.screen.WriteReviewScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    val navGraph = navController.createGraph(startDestination = "car_details_screen/{carId}") {
        addComposableDestinations(this, navController)
    }
    NavHost(navController = navController, graph = navGraph)
}

private fun addComposableDestinations(builder: NavGraphBuilder, navController: NavController) {
    builder.composable("car_details_screen/{carId}") { backStackEntry ->
        // Debug: Log raw arguments
        println("Raw arguments: ${backStackEntry.arguments}")
        val carId = backStackEntry.arguments?.getString("carId")?.toIntOrNull() ?: 0
        println("Parsed carId: $carId")

        CarDetailsScreen(navController = navController, carId = carId)
    }
    builder.composable("write_review_screen/{carId}") { backStackEntry ->
        val carId = backStackEntry.arguments?.getString("carId")?.toIntOrNull() ?: 0
        WriteReviewScreen(navController = navController, carId = carId)
    }
}
