package eu.tutorial.moodle.ui.post

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import eu.tutorial.moodle.ui.navigation.HomeDestination
import kotlinx.coroutines.launch
@Composable
fun SaveTypeDialog(
    save : (String) -> Unit,
    onChange : (Boolean) -> Unit,
){
    var text by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onChange(false) },
        title = { Text("Enter Text") },
        text = {
            BasicTextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                },
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    save(text)
                    onChange(false)
                }
            ) {
                Text(text = "저장")
            }
        }
    )
}