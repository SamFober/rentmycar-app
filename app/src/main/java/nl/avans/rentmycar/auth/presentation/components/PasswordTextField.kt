package nl.avans.rentmycar.auth.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import nl.avans.rentmycar.R

@Composable
fun PasswordTextField(
    value: String,
    onTextChanged: (text: String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = {
            onTextChanged(it)
        },
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = stringResource(R.string.password_field))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = PasswordVisualTransformation(),
        maxLines = 1
    )
}