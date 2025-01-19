package nl.avans.rentmycar.rental.presentation

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
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore

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
        var brand by remember { mutableStateOf(TextFieldValue("")) }
        var model by remember { mutableStateOf(TextFieldValue("")) }
        var availability by remember { mutableStateOf(false) }
        var location by remember { mutableStateOf(TextFieldValue("")) }
        var price by remember { mutableStateOf(TextFieldValue("")) }

        var capturedImageUri by remember { mutableStateOf<Uri?>(null) }
        var capturedImageBitmap by remember { mutableStateOf<Bitmap?>(null) }

        val context = LocalContext.current


        fun getBitmapFromUri(context: Context, uri: Uri): Bitmap? {
            return try {
                context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    BitmapFactory.decodeStream(inputStream)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        val cameraLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture()
        ) { isSuccess: Boolean ->
            if (isSuccess) {
                capturedImageUri?.let {
                    capturedImageBitmap = getBitmapFromUri(context, it)
                }
            }
        }

        fun createImageUri(): Uri {
            val contentResolver = context.contentResolver
            val values = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, "image_${System.currentTimeMillis()}.jpg")
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)  // Save to Pictures
            }
            return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!
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
                val uri = createImageUri()  // Create the image URI
                capturedImageUri = uri
                cameraLauncher.launch(uri)
            }
        ) {
            Text("Take Picture")
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


