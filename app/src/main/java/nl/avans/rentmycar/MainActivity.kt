package nl.avans.rentmycar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import nl.avans.rentmycar.rental.presentation.AllRentals
import nl.avans.rentmycar.rental.presentation.CarDetailsScreen
import nl.avans.rentmycar.rental.presentation.RentalList
import nl.avans.rentmycar.rental.presentation.RentalScreen
import nl.avans.rentmycar.ui.theme.RentMyCarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RentMyCarTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = StartScreen
                ) {
                    composable<StartScreen> {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            AllRentals(RentalList,
                                onDetailButtonPressed = {carId, name, description, imgRes ->
                                navController.navigate(DetailScreen(
                                    carId = carId,
                                    name = name,
                                    description = description,
                                    picture = imgRes
                                ))
                            })
                        }
                    }
                    composable<DetailScreen> {
                        val args = it.toRoute<DetailScreen>()
                        CarDetailsScreen(
                            carId = args.carId,
                            name = args.name,
                            description = args.description,
                            image = args.picture,
                            onRentButtonClick = {
                                navController.navigate(RentalScreen)
                            }
                        )
                    }
                    composable<RentalScreen> {
                        RentalScreen()
                    }
                }
            }
        }
    }
}

@Serializable
object StartScreen

@Serializable
data class DetailScreen(
    val carId: Int,
    val name: String,
    val description: String,
    val picture: Int
)

@Serializable
object RentalScreen