package nl.avans.rentmycar.auth.presentation.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class RegisterViewModel() : ViewModel() {
    private val _state = MutableStateFlow(RegisterUiState())
    val state = _state


}