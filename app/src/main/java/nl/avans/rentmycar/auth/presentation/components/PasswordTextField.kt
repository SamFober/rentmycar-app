package nl.avans.rentmycar.auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nl.avans.rentmycar.R

@Composable
fun PasswordTextField(
    value: String = "",
    visible: Boolean = false,
    onTextChanged: (text: String) -> Unit,
    onPasswordVisibilityChanged: (Boolean) -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {

    val icon = if (visible)
        painterResource(R.drawable.visibility_24px)
    else
        painterResource(R.drawable.visibility_off_24px)

    TextField(
        value = value,
        enabled = enabled,
        onValueChange = {
            onTextChanged(it)
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = {
                onPasswordVisibilityChanged(!visible)
            }) {
                Icon(
                    painter = icon,
                    contentDescription = "Visibility Icon"
                )
            }
        },
        label = {
            Text(text = stringResource(R.string.password_field))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (visible) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}

@Preview
@Composable
fun PasswordTextFieldPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        PasswordTextField(
            value = "TEST",
            onTextChanged = {},
            onPasswordVisibilityChanged = {}
        )
    }
}