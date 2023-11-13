package eu.tutorial.moodle.ui.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PasswordTextField(
    value: String,
    length: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    onValueChange: (String) -> Unit,
) {
    val spaceBetweenBoxes = 8.dp
    val focusManager = LocalFocusManager.current
    focusManager.clearFocus()

    val density = LocalDensity.current.density
    val context = LocalContext.current

    BasicTextField(
        modifier = modifier,
        value = value,
        singleLine = true,
        onValueChange = {
            if (it.length <= length) {
                onValueChange(it)
            }
        },
        readOnly = true,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(spaceBetweenBoxes),
            ) {
                repeat(length) { index ->
                    val isFilled = index < value.length
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(18.dp))
                            .background(Color(0XFF212122)),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isFilled) {
                            Image(
                                painter = painterResource(id = eu.tutorial.moodle.R.drawable.password),
                                contentDescription = "password",
                                modifier = Modifier.size(16.dp)
                            )
                        } else {
                            Text(
                                text = ""
                            )
                        }
                    }
                }
            }
        }
    )
}

