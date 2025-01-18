package nl.avans.rentmycar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import nl.avans.rentmycar.navigation.AppNavigation
import nl.avans.rentmycar.ui.theme.RentMyCarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RentMyCarTheme {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}
