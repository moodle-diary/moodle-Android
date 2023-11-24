package eu.tutorial.moodle.ui.post

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import eu.tutorial.moodle.ui.post.component.PostItemBox
import eu.tutorial.moodle.ui.theme.containerGray

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

    var currentIndex by remember { mutableStateOf(-1) }

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
            visibleChange = {
                // 현재 버튼을 클릭하면 해당 버튼의 가시성을 켜고 다른 버튼들의 가시성을 끕니다.
                currentIndex = if (currentIndex == 0) -1 else 0
            }
        )
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
            visibleChange = {
                // 현재 버튼을 클릭하면 해당 버튼의 가시성을 켜고 다른 버튼들의 가시성을 끕니다.
                currentIndex = if (currentIndex == 1) -1 else 1
            }
        )
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
            visibleChange = {
                // 현재 버튼을 클릭하면 해당 버튼의 가시성을 켜고 다른 버튼들의 가시성을 끕니다.
                currentIndex = if (currentIndex == 2) -1 else 2
            }
        )
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
            },
            visibleChange = {
                // 현재 버튼을 클릭하면 해당 버튼의 가시성을 켜고 다른 버튼들의 가시성을 끕니다.
                currentIndex = if (currentIndex == 3) -1 else 3
            }
        )
    }

}