package nl.avans.rentmycar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nl.avans.rentmycar.ui.review.ReviewFeature

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "reviews/car123") {

        // Reviews screen with carId as argument
        composable("reviews/{carId}") { backStackEntry ->
            val carId = backStackEntry.arguments?.getString("carId") ?: ""
            ReviewFeature(carId = carId, navController = navController)
        }
    }
}

fun navigateToReviews(navController: NavHostController, carId: String) {
    navController.navigate("reviews/$carId")
}
