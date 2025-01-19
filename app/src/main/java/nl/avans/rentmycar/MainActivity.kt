package nl.avans.rentmycar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import nl.avans.rentmycar.auth.domain.TokenManager
import nl.avans.rentmycar.auth.presentation.login.LoginScreenRoute
import nl.avans.rentmycar.auth.presentation.register.RegisterScreenRoute
import nl.avans.rentmycar.ui.theme.RentMyCarTheme
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RentMyCarTheme {
                val navController = rememberNavController()
                val tokenManager = koinInject<TokenManager>()
                var startScreen: Any = MainScreen

                LaunchedEffect(key1 = null) {
                    if (tokenManager.getAccessToken().isEmpty()) {
                        startScreen = LoginScreen
                    }
                }



                NavHost(
                    navController = navController,
                    startDestination = startScreen
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
                    composable<MainScreen> {
                        Text("MAIN SCREEN")
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
object MainScreen