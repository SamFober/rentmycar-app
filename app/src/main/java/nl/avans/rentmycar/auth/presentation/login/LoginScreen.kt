package nl.avans.rentmycar.auth.presentation.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nl.avans.rentmycar.auth.presentation.components.EmailTextField
import nl.avans.rentmycar.auth.presentation.components.PasswordTextField
import nl.avans.rentmycar.core.presentation.util.ObserveAsEvents
import nl.avans.rentmycar.ui.theme.RentMyCarTheme
import org.koin.androidx.compose.koinViewModel
import nl.avans.rentmycar.core.presentation.util.toString
import nl.avans.rentmycar.R

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    ObserveAsEvents(events = viewModel.loginEvents) { event ->
        when (event) {
            is LoginEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.toString(context),
                    Toast.LENGTH_LONG
                ).show()
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

    val contentColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.login),
            fontSize = 40.sp,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            color = contentColor
        )
        Spacer(modifier = Modifier.height(80.dp))
        EmailTextField(state.emailAddress, onTextChanged = {
            viewModel.onAction(LoginAction.EmailChanged(it))
        })
        Spacer(modifier = Modifier.height(20.dp))
        PasswordTextField(state.password, onTextChanged = {
            viewModel.onAction(LoginAction.PasswordChanged(it))
        })
        Spacer(modifier = Modifier.height(20.dp))
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = { viewModel.onAction(LoginAction.Submit) }
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
            viewModel = LoginViewModel()
        )
    }
}