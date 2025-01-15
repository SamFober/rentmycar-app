package nl.avans.rentmycar.auth.presentation.login

import android.widget.Toast
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nl.avans.rentmycar.R
import nl.avans.rentmycar.auth.domain.login.LoginError
import nl.avans.rentmycar.auth.presentation.components.PasswordTextField
import nl.avans.rentmycar.core.domain.util.NetworkError
import nl.avans.rentmycar.core.presentation.util.ObserveAsEvents
import nl.avans.rentmycar.core.presentation.util.toString
import nl.avans.rentmycar.ui.theme.RentMyCarTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoute(
    viewModel: LoginViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    ObserveAsEvents(events = viewModel.loginEvents) { event ->
        when (event) {
            is LoginEvent.Failed -> {
                if (event.error is LoginError) {
                    Toast.makeText(
                        context,
                        event.error.toString(context = context),
                        Toast.LENGTH_LONG
                    ).show()
                } else if (event.error is NetworkError) {
                    Toast.makeText(
                        context,
                        event.error.toString(context = context),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            is LoginEvent.Success -> {
                Toast.makeText(
                    context,
                    "Welkom!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    LoginScreen(
        uiState = state,
        onEmailTextChange = {
            viewModel.updateEmail(it)
        },
        onPasswordTextChange = {
            viewModel.updatePassword(it)
        },
        onSubmitButtonClicked = {
            viewModel.submit()
        },
        onPasswordVisibilityChange = {
            viewModel.updatePasswordVisibility(it)
        }
    )
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEmailTextChange: (String) -> Unit,
    onPasswordTextChange: (String) -> Unit,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    onSubmitButtonClicked: () -> Unit,
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
            text = stringResource(R.string.login),
            fontSize = 40.sp,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            color = contentColor
        )
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            value = uiState.emailAddress,
            enabled = !uiState.isLoading,
            onValueChange = {
                onEmailTextChange(it)
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
                onPasswordTextChange(it)
            },
            onPasswordVisibilityChanged = {
                onPasswordVisibilityChange(it)
            },
            enabled = !uiState.isLoading
        )
        Spacer(modifier = Modifier.height(20.dp))
        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = { onSubmitButtonClicked() }
            ) {
                Text(stringResource(R.string.login))
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun LoginScreenPreview() {
    RentMyCarTheme {
        LoginScreen(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
            uiState = LoginUiState(),
            onEmailTextChange = {},
            onPasswordTextChange = {},
            onSubmitButtonClicked = {},
            onPasswordVisibilityChange = {}
        )
    }
}