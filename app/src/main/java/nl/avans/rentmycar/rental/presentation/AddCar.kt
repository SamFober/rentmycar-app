package nl.avans.rentmycar.rental.presentation

import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import nl.avans.rentmycar.R
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import android.Manifest
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCar() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("RentMyCar") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        val context = LocalContext.current
        var brand by remember { mutableStateOf(TextFieldValue("")) }
        var model by remember { mutableStateOf(TextFieldValue("")) }
        var availability by remember { mutableStateOf(false) }
        var location by remember { mutableStateOf(TextFieldValue("")) }
        var price by remember { mutableStateOf(TextFieldValue("")) }
        var carImage by remember { mutableStateOf<Bitmap?>(null) }
        val lifecycleOwner = LocalContext.current as LifecycleOwner
        val cameraExecutor: ExecutorService = remember { Executors.newSingleThreadExecutor() }
        var imageCapture: ImageCapture? by remember { mutableStateOf(null) }
        val cameraPermission = Manifest.permission.CAMERA

        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
            } else {
                Toast.makeText(context, "Camera permission is required", Toast.LENGTH_SHORT).show()
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Add a New Car", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = brand,
            onValueChange = { brand = it },
            label = { Text("Car Brand") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = model,
            onValueChange = { model = it },
            label = { Text("Car Model") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column( modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
            horizontalAlignment = Alignment.Start
        ){
            Text("Car Type")
        }

        DropDownDemo()

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Available")
            Checkbox(
                checked = availability,
                onCheckedChange = { availability = it }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (ContextCompat.checkSelfPermission(context, cameraPermission) == PackageManager.PERMISSION_GRANTED) {
                    startCamera(
                        lifecycleOwner = lifecycleOwner,
                        onImageCapture = { uri ->
                            carImage = BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri))
                        },
                        onError = { Log.e("CameraX", "Failed to capture image: ${it.message}") }
                    )
                } else {
                    permissionLauncher.launch(cameraPermission)
                }
            }
        ) {
            Text("Take Picture")
        }

        carImage?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Car Image",
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Handle submit action */ }
        ) {
            Text("Submit")
        }
    }
}
}

@Composable
fun DropDownDemo() {

    val isDropDownExpanded = remember {
        mutableStateOf(false)
    }

    val itemPosition = remember {
        mutableStateOf(0)
    }

    val fueltypes = listOf("Elektrisch", "Hybride", "Benzine")

    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopStart
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        isDropDownExpanded.value = true
                    }
            ) {
                Text(
                    text = fueltypes[itemPosition.value],
                    modifier = Modifier.weight(1f)
                )
                Image(
                    painter = painterResource(id = R.drawable.drop_down_ic),
                    contentDescription = "DropDown Icon"
                )
            }
            DropdownMenu(
                expanded = isDropDownExpanded.value,
                onDismissRequest = {
                    isDropDownExpanded.value = false
                }
            ) {
                fueltypes.forEachIndexed { index, fueltype ->
                    DropdownMenuItem(
                        text = {
                            Text(text = fueltype)
                        },
                        onClick = {
                            isDropDownExpanded.value = false
                            itemPosition.value = index
                        }
                    )
                }
            }
        }
    }
}

fun startCamera(
    lifecycleOwner: LifecycleOwner,
    onImageCapture: (Uri) -> Unit,
    onError: (Exception) -> Unit
) {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(lifecycleOwner as android.content.Context)
    cameraProviderFuture.addListener({
        val cameraProvider = cameraProviderFuture.get()
        val preview = Preview.Builder().build().also {
            it.setSurfaceProvider(PreviewView(lifecycleOwner).surfaceProvider)
        }

        val imageCapture = ImageCapture.Builder().build()

        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                CameraSelector.DEFAULT_BACK_CAMERA,
                preview,
                imageCapture
            )

            // Capture logic (for example, a button trigger to take a photo)
            val photoFile = File(lifecycleOwner.filesDir, "photo.jpg")
            val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
            imageCapture.takePicture(
                outputOptions,
                ContextCompat.getMainExecutor(lifecycleOwner as android.content.Context),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                        output.savedUri?.let(onImageCapture)
                    }

                    override fun onError(exception: ImageCaptureException) {
                        onError(exception)
                    }
                }
            )
        } catch (exc: Exception) {
            onError(exc)
        }
    }, ContextCompat.getMainExecutor(lifecycleOwner as android.content.Context))
}

