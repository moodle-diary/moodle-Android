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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GoodDialog(
    navController: NavController,
    visibility: Boolean,
    viewModel: PostViewModel,
    onChange: (Boolean) -> Unit
) {
    if (visibility) {
        val coroutineScope = rememberCoroutineScope()
        val dialogText = "기분 좋은 상태로 저장할까요?"
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
                        .background(Color(0XFF212122))
                ) {
                    Text(
                        text = dialogText,
                        modifier = Modifier.padding(top = 38.dp, bottom = 36.dp),
                        fontSize = 16.sp,
                        color = Color(0XFFDFDFDF),
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
                                text = "취소",
                                fontSize = 12.sp,
                                color = Color(0XFF888888),
                                fontFamily = FontFamily(Font(R.font.poppins_bold))
                            )
                        }
                        Button(
                            onClick = {
                                //TODO : Transaction 고려
                                coroutineScope.launch {
                                    navController.navigate(HomeDestination.route)

                                    val key = viewModel.saveDiary()

                                    viewModel.saveEmotions(
                                        description = "행복",
                                        diaryId = key
                                    )

                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFD9D9D9),
                                contentColor = Color(0XFF151515)
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
                                text = "저장하기",
                                fontSize = 14.sp,
                                color = Color(0XFF151515),
                                fontFamily = FontFamily(Font(R.font.poppins_bold))
                            )
                        }
                    }
                }
            }
        }

    }
}
