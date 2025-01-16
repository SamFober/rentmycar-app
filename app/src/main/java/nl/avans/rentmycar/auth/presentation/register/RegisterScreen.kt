package nl.avans.rentmycar.auth.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalDate
import nl.avans.rentmycar.R
import nl.avans.rentmycar.auth.presentation.components.PasswordTextField
import nl.avans.rentmycar.ui.theme.RentMyCarTheme

@Composable
fun RegisterScreenRoute() {
    RegisterScreen(
        RegisterUiState(),
        onFirstNameTextChanged = {},
        onLastNameTextChanged = {},
        onDateOfBirthChanged = {},
        onEmailTextChanged = {},
        onPasswordTextChanged = {},
        onPasswordVisibilityChanged = {},
        onRegisterButtonClicked = {}
    )
}

@Composable
fun RegisterScreen(
    uiState: RegisterUiState,
    modifier: Modifier = Modifier,
    onFirstNameTextChanged: (String) -> Unit,
    onLastNameTextChanged: (String) -> Unit,
    onDateOfBirthChanged: (LocalDate) -> Unit,
    onEmailTextChanged: (String) -> Unit,
    onPasswordTextChanged: (String) -> Unit,
    onPasswordVisibilityChanged: (Boolean) -> Unit,
    onRegisterButtonClicked: () -> Unit
) {
    val focusRequester = remember {
        FocusRequester()
    }

    val focusManager = LocalFocusManager.current

    val contentColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }

    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Register",
            fontSize = 40.sp,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            color = contentColor
        )
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            value = uiState.firstName,
            enabled = !uiState.isLoading,
            onValueChange = {
                onFirstNameTextChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            singleLine = true,
            label = {
                Text(text = stringResource(R.string.first_name_field))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = uiState.lastName,
            enabled = !uiState.isLoading,
            onValueChange = {
                onLastNameTextChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            singleLine = true,
            label = {
                Text(text = stringResource(R.string.last_name_field))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = uiState.emailAddress,
            enabled = !uiState.isLoading,
            onValueChange = {
                onEmailTextChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            singleLine = true,
            label = {
                Text(text = stringResource(R.string.email_field))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        PasswordTextField(
            value = uiState.password,
            visible = uiState.passwordVisible,
            onTextChanged = {
                onPasswordTextChanged(it)
            },
            onPasswordVisibilityChanged = {
                onPasswordVisibilityChanged(it)
            },
            enabled = !uiState.isLoading
        )
        Spacer(modifier = Modifier.height(20.dp))
        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = { onRegisterButtonClicked() }
            ) {
                Text(stringResource(R.string.login))
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RentMyCarTheme {
        RegisterScreen(
            RegisterUiState(),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
            onFirstNameTextChanged = {},
            onLastNameTextChanged = {},
            onDateOfBirthChanged = {},
            onEmailTextChanged = {},
            onPasswordTextChanged = {},
            onPasswordVisibilityChanged = {},
            onRegisterButtonClicked = {}
        )
    }
}