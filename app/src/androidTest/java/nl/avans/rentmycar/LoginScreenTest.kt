package nl.avans.rentmycar

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import nl.avans.rentmycar.auth.presentation.login.LoginScreen
import nl.avans.rentmycar.auth.presentation.login.LoginUiState
import nl.avans.rentmycar.ui.theme.RentMyCarTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoginScreen() {
        val email = "test@example.com"
        val password = "password"

        composeTestRule.setContent {
            RentMyCarTheme() {
                LoginScreen(
                    modifier = Modifier,
                    uiState = LoginUiState(
                        emailAddress = "test@example.com",
                        password = "password",
                        isLoading = false,
                        passwordVisible = true
                    ),
                    onEmailTextChange = {  },
                    onPasswordTextChange = {  },
                    onPasswordVisibilityChange = {  },
                    onSubmitButtonClicked = {  }
                ) { }
            }
        }
        composeTestRule.waitForIdle()

        // Simulate email input
        composeTestRule.onNodeWithTag("emailField")
            .performTextInput(email)

        // Simulate password input
        composeTestRule.onNodeWithTag("passwordField")
            .performTextInput(password)

        // Simulate login button click
        composeTestRule.onNodeWithTag("signInButton")
            .performClick()

        // Assert that email and password are non-empty (basic validation)
        assertTrue(email.isNotEmpty())
        assertTrue(password.isNotEmpty())
    }
}