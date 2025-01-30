package nl.avans.rentmycar.auth.presentation.login

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import nl.avans.rentmycar.ui.theme.RentMyCarTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            RentMyCarTheme {
                LoginScreen(
                    uiState = LoginUiState(),
                    onEmailTextChange = {},
                    onPasswordTextChange = {},
                    onPasswordVisibilityChange = {},
                    onSubmitButtonClicked = {},
                    onRegisterButtonClicked = {}
                )
            }
        }
    }

    @Test
    fun loginScreen_showsUIElements() {
        composeTestRule.onNodeWithTag("login_title").assertIsDisplayed()
        composeTestRule.onNodeWithTag("email_field").assertIsDisplayed()
        composeTestRule.onNodeWithTag("password").assertIsDisplayed()
        composeTestRule.onNodeWithTag("login_button").assertIsDisplayed()
        composeTestRule.onNodeWithTag("register_button").assertIsDisplayed()
    }

    @Test
    fun loginScreen_loginButtonClick() {
        composeTestRule.onNodeWithTag("login_button").performClick()
        composeTestRule.onNodeWithTag("loading_indicator").assertExists()
    }

    @Test
    fun loginScreen_togglePasswordVisibility() {
        composeTestRule.onNodeWithTag("password").assertExists()
        composeTestRule.onNodeWithTag("visibility_toggle").performClick()
    }

    @Test
    fun loginScreen_enterCredentialsAndSubmit() {
        // Enter email
        composeTestRule.onNodeWithTag("email_field").performTextInput("test@example.com")

        // Enter password
        composeTestRule.onNodeWithTag("password").performTextInput("SecurePassword123!")

        // Click login button
        composeTestRule.onNodeWithTag("login_button").performClick()

        // Verify loading indicator appears (if applicable)
        composeTestRule.onNodeWithTag("loading_indicator").assertExists()
    }
}
