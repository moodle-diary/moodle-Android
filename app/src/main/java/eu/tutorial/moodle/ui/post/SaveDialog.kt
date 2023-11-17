package eu.tutorial.moodle.ui.post

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.navigation.HomeDestination
import kotlinx.coroutines.launch
import eu.tutorial.moodle.data.local.emotionData
import eu.tutorial.moodle.ui.theme.backgroundGray
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.contentGray
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SaveDialog(
    emotionButtonStates: SnapshotStateList<SnapshotStateList<Boolean>>,
    causeButtonStates: SnapshotStateList<Boolean>,
    placeButtonStates: SnapshotStateList<Boolean>,
    isCancel: Boolean,
    navController: NavController,
    viewModel: PostViewModel,
    onChange: (Boolean) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val dialogText = if (isCancel) {
        "저장하지 않고 나가겠어요?"
    } else {
        "지금까지의 내용을 저장하시겠어요?"
    }
    Dialog(
        onDismissRequest = { onChange(false) },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            shape = RoundedCornerShape(32.dp),
            modifier = Modifier
                .width(282.dp)
                .height(175.dp)
                .background(Color.Transparent)
                .clip(shape = CircleShape.copy(all = CornerSize(32.dp)))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundGray)
            ) {
                Text(
                    text = dialogText,
                    modifier = Modifier.padding(top = 38.dp, bottom = 36.dp),
                    fontSize = 16.sp,
                    color = contentBlack,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { onChange(false) },
                        modifier = Modifier
                            .defaultMinSize(
                                minWidth = 131.dp,
                                minHeight = 46.dp
                            )
                            .clip(shape = CircleShape.copy(all = CornerSize(32.dp))),
                        contentPadding = PaddingValues(
                            top = 16.dp,
                            bottom = 16.dp,
                            start = 26.dp,
                            end = 26.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        elevation = ButtonDefaults.buttonElevation( //버튼 그림자 없애기
                            defaultElevation = 0.dp,
                            pressedElevation = 0.dp
                        )
                    ) {
                        Text(
                            text = if (isCancel) {
                                "계속 작성하기"
                            } else {
                                "조금 더 작성하기"
                            },
                            fontSize = 12.sp,
                            color = contentGray,
                            fontFamily = FontFamily(Font(R.font.poppins_bold))
                        )
                    }
                    Button(
                        onClick = {
                            //TODO : Transaction 고려
                            coroutineScope.launch {
                                navController.navigate(HomeDestination.route)

                                val key = viewModel.saveDiary()

                                for (i in 0 until emotionButtonStates.size)
                                    for (j in 0 until emotionButtonStates[i].size)
                                        if (emotionButtonStates[i][j]) viewModel.saveEmotions(
                                            description = emotionData[i * 4 + j],
                                            diaryId = key
                                        )

                                for (i in 0 until causeButtonStates.size)
                                    if (causeButtonStates[i]) viewModel.saveCause(
                                        viewModel.causeTypes[i].causeType, key
                                    )

                                for (i in 0 until placeButtonStates.size)
                                    if (placeButtonStates[i]) viewModel.savePlace(
                                        viewModel.placesTypes[i].placeType, key
                                    )


                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = containerGray,
                            contentColor = contentBlack
                        ),
                        modifier = Modifier
                            .defaultMinSize(
                                minWidth = 98.dp,
                                minHeight = 46.dp
                            )
                            .clip(shape = CircleShape.copy(all = CornerSize(32.dp))),
                        contentPadding = PaddingValues(
                            top = 13.dp,
                            bottom = 13.dp,
                            start = 21.dp,
                            end = 21.dp
                        ),
                    ) {
                        Text(
                            text = if (isCancel) {
                                "나가기"
                            } else {
                                "저장하기"
                            },
                            fontSize = 14.sp,
                            color = contentBlack,
                            fontFamily = FontFamily(Font(R.font.poppins_bold))
                        )
                    }
                }
            }
        }
    }
}
