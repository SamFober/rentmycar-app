import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import nl.avans.rentmycar.auth.presentation.register.RegisterScreen
import nl.avans.rentmycar.auth.presentation.register.RegisterUiState
import org.junit.Rule
import org.junit.Test

class RegisterScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRegisterScreen() {

        composeTestRule.setContent {
            RegisterScreen(
                uiState = RegisterUiState(),
                modifier = Modifier,
                onFirstNameTextChanged = {},
                onLastNameTextChanged = {},
                onDateOfBirthChanged = {},
                onEmailTextChanged = {},
                onPasswordTextChanged = {},
                onPasswordVisibilityChanged = {},
                onRegisterButtonClicked = {},
                onLoginButtonClicked = {}
            )
        }
        composeTestRule.waitForIdle()
        // Assert that the form fields are present
        composeTestRule.onNodeWithTag("firstNameField").assertExists()
        composeTestRule.onNodeWithTag("lastNameField").assertExists()
        composeTestRule.onNodeWithTag("emailField").assertExists()
        composeTestRule.onNodeWithTag("passwordField").assertExists()

        // Simulate user input and interactions
        composeTestRule.onNodeWithTag("firstNameField").performTextInput("Jane")
        composeTestRule.onNodeWithTag("lastNameField").performTextInput("Smith")
        composeTestRule.onNodeWithTag("emailField").performTextInput("jane.smith@example.com")
        composeTestRule.onNodeWithTag("passwordField").performTextInput("newpassword123")

        // Simulate button click
        composeTestRule.onNodeWithTag("registerButton").performClick()

        }
}
