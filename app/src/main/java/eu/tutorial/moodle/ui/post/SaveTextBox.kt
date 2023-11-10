package eu.tutorial.moodle.ui.post

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
    textValue :String,
    valueChange: (String) -> Unit
){
    var isTextFieldFocused = false

    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current

    val brush = Brush.horizontalGradient(listOf(Color(0xFF00E1FF), Color(0xFF0099FF)))

    var visible by remember { mutableStateOf(false) }
    val density = LocalDensity.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(color = 0Xff212122))
            .addFocusCleaner(focusManager)
            .then(Modifier.verticalScroll(rememberScrollState())),
    ) {
        BasicTextField(
            value = textValue,
            onValueChange = { valueChange(it) },
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = Color(0XFFEDEDED)
            ),
            cursorBrush = SolidColor(Color(0XFFEDEDED)),
            modifier = Modifier
                .height(200.dp)
                .padding(16.dp)
                .background(Color(color = 0Xff212122))
                .focusRequester(focusRequester = focusRequester)
                .onFocusChanged {
                    isTextFieldFocused = it.isFocused
                },
            decorationBox = { innerTextField ->
                if (textValue.isEmpty()) {
                    Text(
                        text = "이곳에 생각을 작성하세요.",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color(0XFF888888)
                    )
                }
                innerTextField()
            }
        )

        //1
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(),
            onClick = { visible = !visible},
            modifier = Modifier
                .padding(start = 16.dp)
                .width(if (visible) 247.dp else 247.dp)
                .height(if (visible) 52.dp else 52.dp)
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (!visible) Icons.Default.Add else Icons.Default.KeyboardArrowDown,
                    contentDescription = "add",
                    tint = Color.White
                )
                Text(
                    text = "그 사람은 왜 그런 행동을 했을까?",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Color(0XFFEDEDED),
                    modifier = Modifier.padding(start = if (!visible) 12.dp else 12.dp)
                )
            }
        }
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(
                initialOffsetY = { with(density) { -40.dp.roundToPx() } },
                animationSpec = tween(durationMillis = 500)
            ) + expandVertically(expandFrom = Alignment.Top, animationSpec = tween(durationMillis = 500))
                    + fadeIn(animationSpec = tween(durationMillis = 500, delayMillis = 200)),
            exit = slideOutVertically(
                targetOffsetY = { with(density) { 0.dp.roundToPx() } },
                animationSpec = tween(durationMillis = 500))
                    + shrinkVertically(animationSpec = tween(durationMillis = 500, delayMillis = 200))
                    + fadeOut(animationSpec = tween(durationMillis = 300, delayMillis = 200))
        ) {
            BasicTextField(
                value = textValue,
                onValueChange = { valueChange(it) },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0XFFEDEDED)
                ),
                cursorBrush = SolidColor(Color(0XFFEDEDED)),
                modifier = Modifier
                    .height(200.dp)
                    .padding(16.dp)
                    .background(Color(color = 0Xff212122))
                    .focusRequester(focusRequester = focusRequester)
                    .onFocusChanged {
                        isTextFieldFocused = it.isFocused
                    },
                decorationBox = { innerTextField ->
                    if (textValue.isEmpty()) {
                        Text(
                            text = "이 곳에 답변을 적어주세요.",
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = Color(0XFF888888)
                        )
                    }
                    innerTextField()
                }
            )
        }

        //2
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(),
            onClick = {  },
            modifier = Modifier
                .padding(start = 16.dp, top = 18.dp)
                .width(228.dp)
                .height(52.dp)
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add",
                    tint = Color.White
                )
                Text(
                    text = "나는 왜 그런 감정을 느꼈을까?",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Color(0XFFEDEDED),
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }
        //3
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(),
            onClick = {  },
            modifier = Modifier
                .padding(start = 16.dp, top = 18.dp)
                .width(191.dp)
                .height(52.dp)
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add",
                    tint = Color.White
                )
                Text(
                    text = "다른 이유가 있을까?",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Color(0XFFEDEDED),
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }

    }

}