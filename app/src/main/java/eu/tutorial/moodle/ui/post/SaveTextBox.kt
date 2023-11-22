package eu.tutorial.moodle.ui.post

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.post.component.PostItemBox
import eu.tutorial.moodle.ui.theme.backgroundGray
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.contentCharcoal
import eu.tutorial.moodle.ui.theme.contentGray
import eu.tutorial.moodle.ui.theme.mainOrange
import eu.tutorial.moodle.ui.theme.subYellow


fun Modifier.addFocusCleaner(focusManager: FocusManager, doOnClear: () -> Unit = {}): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        })
    }
}

@Composable
fun SaveTextBox(
    textValue: DiaryDetails,
    valueChange: (DiaryDetails) -> Unit
) {
    var isTextFieldFocused = false

    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current

    //val brush = Brush.horizontalGradient(listOf(Color(0xFF00E1FF), Color(0xFF0099FF)))
    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(containerGray)
            .addFocusCleaner(focusManager)
            .then(Modifier.verticalScroll(rememberScrollState())),
    ) {
        PostItemBox(
            textValue = textValue.diaryText,
            currentIndex = currentIndex,
            index = 0,
            focusChange = { focus ->
                isTextFieldFocused = focus.isFocused
            },
            textChange = {
                valueChange(
                    textValue.copy(
                        diaryText = it
                    )
                )
            },
        ) {
            currentIndex = 0
        }
        //1
        PostItemBox(
            textValue = textValue.act,
            currentIndex = currentIndex,
            index = 1,
            focusChange = { focus ->
                isTextFieldFocused = focus.isFocused
            },
            textChange = {
                valueChange(
                    textValue.copy(
                        act = it
                    )
                )
            },
        ) {
            currentIndex = 1
        }
        PostItemBox(
            textValue = textValue.thought,
            currentIndex = currentIndex,
            index = 2,
            focusChange = { focus ->
                isTextFieldFocused = focus.isFocused
            },
            textChange = {
                valueChange(
                    textValue.copy(
                        thought = it
                    )
                )
            },

            ) {
            currentIndex = 2
        }
        PostItemBox(
            textValue = textValue.feeling,
            currentIndex = currentIndex,
            index = 3,
            focusChange = { focus ->
                isTextFieldFocused = focus.isFocused
            },
            textChange = {
                valueChange(
                    textValue.copy(
                        feeling = it
                    )
                )
            }
        ) {
            currentIndex = 3
        }
    }

}