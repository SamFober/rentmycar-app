package nl.avans.rentmycar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import nl.avans.rentmycar.auth.presentation.login.LoginScreenRoute
import nl.avans.rentmycar.auth.presentation.register.RegisterScreenRoute
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
                            onRegisterButtonClicked = { navController.navigate(RegisterScreen) }
                        )
                    }
                    composable<RegisterScreen> {
                        RegisterScreenRoute(
                            onLoginButtonClicked = {
                                navController.popBackStack(LoginScreen, inclusive = false)
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