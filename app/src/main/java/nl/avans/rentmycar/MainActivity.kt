package nl.avans.rentmycar

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import nl.avans.rentmycar.rental.presentation.AddCar
import nl.avans.rentmycar.rental.presentation.AllRentals
import nl.avans.rentmycar.rental.presentation.MyCarDetailsScreen
import nl.avans.rentmycar.rental.presentation.DetailScreen
import nl.avans.rentmycar.rental.presentation.RentalList
import nl.avans.rentmycar.rental.presentation.StartScreen
import nl.avans.rentmycar.ui.theme.RentMyCarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(
                this, CAMERAX_PERMISSIONS, 0
            )
        }
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
                            AllRentals(
                                RentalList,
                                onDetailButtonPressed = {
                                    navController.navigate(DetailScreen(it))
                                },
                                onAddCarButtonPressed = {
                                    navController.navigate("add_car")
                                }
                            )
                        }
                    }
                    composable<DetailScreen> {
                        val args = it.toRoute<DetailScreen>()
                        MyCarDetailsScreen(args.carId)
                    }
                    composable("add_car") {
                        AddCar()
                    }
                }
            }
        }
    }

    private fun hasRequiredPermissions(): Boolean {
        return CAMERAX_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                applicationContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        private val CAMERAX_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
        )
    }
}