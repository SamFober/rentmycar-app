package nl.avans.rentmycar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import nl.avans.rentmycar.auth.presentation.login.LoginScreenRoute
import nl.avans.rentmycar.auth.presentation.register.RegisterScreenRoute
import nl.avans.rentmycar.rental.presentation.CarDetailsScreen
import nl.avans.rentmycar.rental.presentation.booking.BookRentalScreenRoute
import nl.avans.rentmycar.rental.presentation.offer.RentalOfferListScreenRoute
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
                    startDestination = LoginScreen
                ) {
                    composable<LoginScreen> {
                        LoginScreenRoute(
                            onRegisterButtonClicked = { navController.navigate(RegisterScreen) },
                            onUserLoggedIn = {
                                navController.navigate(MainScreen) {
                                    popUpTo(LoginScreen) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    composable<RegisterScreen> {
                        RegisterScreenRoute(
                            onLoginButtonClicked = {
                                navController.popBackStack(LoginScreen, inclusive = false)
                            }
                        )
                    }
                    composable<MainScreen> {
                        RentalOfferListScreenRoute(
                            onDetailButtonPressed = { carId, name, description, imgUrl ->
                                navController.navigate(
                                    DetailScreen(
                                        carId = carId.toString(),
                                        name = name,
                                        description = description,
                                        imgUrl = imgUrl
                                    )
                                )
                            }
                        )
                    }
                    composable<DetailScreen> {
                        val args = it.toRoute<DetailScreen>()
                        CarDetailsScreen(
                            carId = args.carId.toString(),
                            name = args.name,
                            description = args.description,
                            imgUrl = args.imgUrl,
                            onRentButtonClick = {
                                navController.navigate(BookRentalScreen(it.toString()))
                            }
                        )
                    }
                    composable<BookRentalScreen> {
                        BookRentalScreenRoute(
                            onBookingAdded = {
                                navController.popBackStack(MainScreen, inclusive = false)
                            }
                        )
                    }
                    composable<BookingListScreen> {

                    }
                }
            }
        }
    }
}

@Serializable
object LoginScreen

@Serializable
object RegisterScreen

@Serializable
object BookingListScreen

@Serializable
object MainScreen

@Serializable
object StartScreen

@Serializable
data class DetailScreen(
    val carId: String,
    val name: String,
    val description: String,
    val imgUrl: String
)

@Serializable
data class BookRentalScreen(
    val offerId: String
)