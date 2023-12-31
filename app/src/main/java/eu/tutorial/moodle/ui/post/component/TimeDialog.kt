package eu.tutorial.moodle.ui.post.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.mainOrange

@ExperimentalMaterial3Api
@Composable
fun TimeDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    timePickerState: TimePickerState,
    onTimeSelected: (Int, Int) -> Unit
) {
    if (showDialog) {
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = containerGray,
                    shape = RoundedCornerShape(size = 18.dp)
                ),
            onDismissRequest = { onDismissRequest() }
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        color = containerGray
                    )
                    .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TimePicker(
                    state = timePickerState,
                    colors = TimePickerDefaults.colors(
                        timeSelectorSelectedContainerColor = containerGray,
                        timeSelectorUnselectedContainerColor = containerGray,
                        timeSelectorSelectedContentColor = Color.Black,
                        timeSelectorUnselectedContentColor = Color(0XFFAEAEB2),
                        clockDialSelectedContentColor = Color.White,
                        clockDialUnselectedContentColor = Color.Black,
                        periodSelectorSelectedContainerColor = mainOrange,
                        periodSelectorSelectedContentColor = Color.Black,
                        periodSelectorBorderColor = containerGray,
                        periodSelectorUnselectedContainerColor = Color.White,
                        periodSelectorUnselectedContentColor = Color.Black,
                        clockDialColor = Color.White,
                        selectorColor = mainOrange
                    )
                )

                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { onDismissRequest() }) {
                        Text(
                            text = "취소",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = Color(0xFF000000),
                        )
                    }

                    TextButton(
                        onClick = {
                            onDismissRequest()
                            onTimeSelected(timePickerState.hour, timePickerState.minute)
                        }
                    ) {
                        Text(
                            text = "저장",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            color = Color(0xFF000000),
                        )
                    }
                }
            }
        }
    }
}