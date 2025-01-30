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
import nl.avans.rentmycar.auth.presentation.login.LoginScreenRoute
import nl.avans.rentmycar.auth.presentation.register.RegisterScreenRoute
import nl.avans.rentmycar.car.presentation.AddCar
import nl.avans.rentmycar.car.presentation.AllRentals
import nl.avans.rentmycar.car.presentation.MyCarDetailsScreen
import nl.avans.rentmycar.car.presentation.RentalList
import nl.avans.rentmycar.rental.presentation.OfferDetailsScreen
import nl.avans.rentmycar.rental.presentation.booking.BookRentalScreenRoute
import nl.avans.rentmycar.rental.presentation.offer.RentalOfferListScreenRoute
import nl.avans.rentmycar.review.presentation.ReviewScreen
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
                                    OfferDetailScreen(
                                        carId = carId.toString(),
                                        name = name,
                                        description = description,
                                        imgUrl = imgUrl
                                    )
                                )
                            },
                            onCarButtonPressed = {
                                navController.navigate(CarListScreen)
                            },
                            onReviewButtonPressed = {
                                navController.navigate(ReviewScreen)
                            }
                        )
                    }
                    composable<OfferDetailScreen> {
                        val args = it.toRoute<OfferDetailScreen>()
                        OfferDetailsScreen(
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
                    composable<CarListScreen> {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            AllRentals(
                                RentalList,
                                onDetailButtonPressed = {
                                    navController.navigate(CarDetailScreen(it))
                                },
                                onAddCarButtonPressed = {
                                    navController.navigate("add_car")
                                }
                            )
                        }
                    }
                    composable<CarDetailScreen> {
                        val args = it.toRoute<CarDetailScreen>()
                        MyCarDetailsScreen(args.carId)
                    }
                    composable("add_car") {
                        AddCar()
                    }
                    composable<ReviewScreen> {
                        ReviewScreen(
                            carId = "",
                            onNavigateBack = {
                                navController.popBackStack()
                            }
                        )
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
object CarListScreen

@Serializable
object MainScreen

@Serializable
object ReviewScreen

@Serializable
data class OfferDetailScreen(
    val carId: String,
    val name: String,
    val description: String,
    val imgUrl: String
)

@Serializable
data class BookRentalScreen(
    val offerId: String
)

@Serializable
data class CarDetailScreen(
    val carId: Int
)