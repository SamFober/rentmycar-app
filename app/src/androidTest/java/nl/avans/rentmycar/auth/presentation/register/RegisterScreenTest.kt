package nl.avans.rentmycar.auth.presentation.register

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import nl.avans.rentmycar.ui.theme.RentMyCarTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun registerScreen_showsUIElements() {
        composeTestRule.setContent {
            RentMyCarTheme {
                RegisterScreen(
                    uiState = RegisterUiState(),
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
        }

        composeTestRule.onNodeWithText("Register").assertIsDisplayed()
        composeTestRule.onNodeWithText("First Name").assertIsDisplayed()
        composeTestRule.onNodeWithText("Last Name").assertIsDisplayed()
        composeTestRule.onNodeWithText("Email").assertIsDisplayed()
        composeTestRule.onNodeWithText("Password").assertIsDisplayed()
        composeTestRule.onNodeWithText("Register").assertHasClickAction()
    }

    @Test
    fun registerScreen_registerButtonClick() {
        composeTestRule.setContent {
            RentMyCarTheme {
                RegisterScreen(
                    uiState = RegisterUiState(),
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
        }

        composeTestRule.onNodeWithText("Register").performClick()
    }

    @Test
    fun registerScreen_invalidInputs_showsErrorMessages() {
        composeTestRule.setContent {
            RentMyCarTheme {
                RegisterScreen(
                    uiState = RegisterUiState(),
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
        }

        composeTestRule.onNodeWithText("Register").performClick()
        composeTestRule.onNodeWithText("First Name is required").assertIsDisplayed()
        composeTestRule.onNodeWithText("Last Name is required").assertIsDisplayed()
        composeTestRule.onNodeWithText("Email is required").assertIsDisplayed()
        composeTestRule.onNodeWithText("Password is required").assertIsDisplayed()
    }

    @Test
    fun registerScreen_successfulRegistration_navigatesToSuccessScreen() {
        composeTestRule.setContent {
            RentMyCarTheme {
                RegisterScreen(
                    uiState = RegisterUiState(registrationSuccessful = true),
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
        }

        composeTestRule.onNodeWithText("Account Created").assertIsDisplayed()
    }
}
