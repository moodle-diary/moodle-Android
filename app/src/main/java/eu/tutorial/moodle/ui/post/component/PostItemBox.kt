package eu.tutorial.moodle.ui.post.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.local.questionList
import eu.tutorial.moodle.ui.theme.backgroundGray
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.contentCharcoal
import eu.tutorial.moodle.ui.theme.contentGray
import eu.tutorial.moodle.ui.theme.mainOrange
import eu.tutorial.moodle.ui.theme.subYellow

@Composable
fun PostItemBox(
    textValue: String,
    currentIndex: Int,
    index: Int,
    focusChange: (FocusState) -> Unit,
    textChange: (String) -> Unit,
    visibleChange: () -> Unit,
) {
    val visible = (currentIndex == index)
    val brush = Brush.horizontalGradient(listOf(mainOrange, subYellow))
    val density = LocalDensity.current
    val focusRequester by remember { mutableStateOf(FocusRequester()) }

    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        onClick = visibleChange,
        modifier = Modifier
            .padding(start = 16.dp, bottom = 12.dp)
            .width(218.dp)
            .height(36.dp)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(brush),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (!visible) Icons.Default.Add else Icons.Default.KeyboardArrowDown,
                contentDescription = "add",
                tint = backgroundGray,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(16.dp)
            )
            Text(
                text = stringResource(id = questionList[index]),
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                color = backgroundGray,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = { with(density) { -40.dp.roundToPx() } },
            animationSpec = tween(durationMillis = 500)
        ) + expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(durationMillis = 500)
        )
                + fadeIn(animationSpec = tween(durationMillis = 500, delayMillis = 200)),
        exit = slideOutVertically(
            targetOffsetY = { with(density) { 0.dp.roundToPx() } },
            animationSpec = tween(durationMillis = 500)
        )
                + shrinkVertically(
            animationSpec = tween(
                durationMillis = 500,
                delayMillis = 200
            )
        )
                + fadeOut(animationSpec = tween(durationMillis = 300, delayMillis = 200))
    ) {
        BasicTextField(
            value = textValue,
            onValueChange = { textChange(it) },
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = contentBlack
            ),
            cursorBrush = SolidColor(contentCharcoal),
            modifier = Modifier
                .height(160.dp)
                .padding(20.dp, 3.dp)
                .background(containerGray)
                .focusRequester(focusRequester = focusRequester)
                .onFocusChanged {
                    focusChange(it)
                },
            decorationBox = { innerTextField ->
                if (textValue.isEmpty()) {
                    Text(
                        text = "이 곳에 답변을 적어주세요.",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = contentGray,
                        textAlign = TextAlign.Center
                    )
                }
                innerTextField()
            }
        )
    }

}