package nl.avans.rentmycar.ui.review.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SortDropdownMenu(
    selectedOption: String,
    onSortOptionChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Newest", "Oldest", "Highest Rating")

    Box(modifier = Modifier.padding(16.dp)) {
        // Dropdown button
        Button(onClick = { expanded = true }) {
            Text(text = selectedOption)
        }

        // Dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onSortOptionChange(option)
                        expanded = false
                    },
                    text = {
                        Text(
                            text = option,
                            style = if (option == selectedOption) MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            ) else MaterialTheme.typography.bodyMedium
                        )
                    }
                )
            }
        }
    }
}