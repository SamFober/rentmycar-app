package nl.avans.rentmycar.auth.presentation.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.datetime.LocalDate
import nl.avans.rentmycar.R
import nl.avans.rentmycar.auth.presentation.components.PasswordTextField
import nl.avans.rentmycar.core.presentation.util.ObserveAsEvents
import nl.avans.rentmycar.ui.theme.RentMyCarTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreenRoute(
    viewModel: RegisterViewModel = koinViewModel(),
    onLoginButtonClicked: () -> Unit
) {
    val loginUiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserveAsEvents(events = viewModel.registerEvents) { event ->
        when (event) {
            is RegisterEvent.Success -> {
                viewModel.updateIsRegistrationSuccessful(true)
            }

            is RegisterEvent.Failed -> {
                Toast.makeText(
                    context,
                    "Mislukt.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    RegisterScreen(
        loginUiState,
        onFirstNameTextChanged = {
            viewModel.updateFirstName(it)
        },
        onLastNameTextChanged = {
            viewModel.updateLastName(it)
        },
        onDateOfBirthChanged = {
            viewModel.updateDateOfBirth(it)
        },
        onEmailTextChanged = {
            viewModel.updateEmailAddress(it)
        },
        onPasswordTextChanged = {
            viewModel.updatePassword(it)
        },
        onPasswordVisibilityChanged = {
            viewModel.updatePasswordVisibility(it)
        },
        onRegisterButtonClicked = {
            viewModel.register()
        },
        onLoginButtonClicked = {
            onLoginButtonClicked()
        }
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
    onRegisterButtonClicked: () -> Unit,
    onLoginButtonClicked: () -> Unit
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

    if (uiState.registrationSuccessful) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.check_mark),
                contentDescription = "Check mark",
                modifier = Modifier
                    .size(150.dp)
            )
            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = stringResource(R.string.account_created),
                textAlign = TextAlign.Center,
                color = contentColor
            )
            Spacer(Modifier.height(30.dp))
            Button(
                onClick = { onLoginButtonClicked() }
            ) {
                Text(stringResource(R.string.login))
            }
        }
    } else {
        Column(
            modifier = modifier
                .padding(10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.register),
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
                    .testTag("firstNameField")
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
                    .testTag("lastNameField")
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
                    .testTag("emailField")
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
                    modifier = Modifier.testTag("registerButton"),
                    onClick = { onRegisterButtonClicked() }
                ) {
                    Text(stringResource(R.string.register))
                }
            }
        }
    }
}

@PreviewLightDark
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
            onRegisterButtonClicked = {},
            onLoginButtonClicked = {},
        )
    }
}

@PreviewLightDark
@Composable
fun RegisterScreenPreviewRegisterSuccessful() {
    RentMyCarTheme {
        RegisterScreen(
            RegisterUiState(registrationSuccessful = true),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
            onFirstNameTextChanged = {},
            onLastNameTextChanged = {},
            onDateOfBirthChanged = {},
            onEmailTextChanged = {},
            onPasswordTextChanged = {},
            onPasswordVisibilityChanged = {},
            onRegisterButtonClicked = {},
            onLoginButtonClicked = {},
        )
    }
}