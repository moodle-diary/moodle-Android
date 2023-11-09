package eu.tutorial.moodle.ui.post

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.theme.poppins
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoodGrid(
    buttonStates : SnapshotStateList<SnapshotStateList<Boolean>>,
    diaryUiState: DiaryUiState,
    onClick : (DiaryDetails) -> Unit,
    navController: NavController
) {

    var showDialog = remember { mutableStateOf(false) }

    val data = listOf(
        "화남", "긴장됨", "행복함", "기쁨",
        "걱정됨", "불편함", "편안함", "신남",
        "외로음", "슬픔", "차분함", "만족함",
        "힘없음", "실망함", "느긋함", "감사함"
    )

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color(color = 0Xff212122))
            .clip(shape = CircleShape.copy(all = CornerSize(45.dp)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "감정 고르기",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                textAlign = TextAlign.Center,
                color = Color(0XFFEDEDED)
            )
            Text(
                modifier = Modifier.padding(top = 4.dp, bottom = 28.dp),
                text = "어떤 감정들을 느낀것 같나요?",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                textAlign = TextAlign.Center,
                color = Color(0XFFEDEDED)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.padding(25.dp, 0.dp)
                //modifier = Modifier.padding(top = 108.dp, start = 20.dp)
            ) {
                itemsIndexed(data) { index, item ->
                    // 각 데이터 아이템을 Row 컴포넌트 내에서 텍스트와 버튼으로 구성
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        val isClicked = buttonStates[index / 4][index % 4]
                        val backgroundColor = if (isClicked) {
                            Color(0XFF414141) //클릭된 경우
                        } else {
                            Color(0XFFC5C1C1) //클릭되지 않은 경우
                        }

                        IconButton(
                            onClick = {
                                // 클릭된 버튼의 상태를 토글합니다.
                                buttonStates[index / 4][index % 4] = !isClicked
                                onClick(
                                    diaryUiState.diaryDetails.copy(
                                        currentDate = LocalDate.now().toString(),
                                        emotions = convertToBitMap(buttonStates)
                                    )
                                )
                            },
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(backgroundColor) // 배경색 설정
                        ) {
                            Icon(
                                Icons.Default.AddCircle,
                                contentDescription = "circle"
                            )
                        }
                        Text(
                            text = item,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .width(40.dp)
                                .padding(top = 4.dp, bottom = 8.dp),
                            color = Color(0XFFEDEDED)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { showDialog.value = true },
                    shape = CircleShape,
                    modifier = Modifier
                        .defaultMinSize(minWidth = 42.dp, minHeight = 42.dp),
                    contentPadding = PaddingValues(bottom = 3.dp),
                    border = BorderStroke(2.dp, Brush.linearGradient(listOf(Color(0XFFFFCF25), Color(0XFFF198FF)))),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0XFF363637),
                    ),
                ) {
                    Text(
                        text = "👍",
                        fontSize = 14.sp
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 15.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "오늘은 유쾌한 날",
                        fontSize = 14.sp,
                        fontFamily = poppins,
                        color = Color(0xFF999999)
                    )
                    Text(
                        text = "기분이 좋으면 눌러요!",
                        fontSize = 11.sp,
                        fontFamily = poppins,
                        color = Color(0xFF999999)
                    )
                }
            }
            if (showDialog.value) {
                GoodDialog(
                    navController = navController,
                ) { dialogVisible -> showDialog.value = dialogVisible }
            }

        }
    }
}

fun convertToBitMap(
    buttonStates :  SnapshotStateList<SnapshotStateList<Boolean>>,
) : Int{
    var result = 0b0000000000000000

    for (i in 0 until 4)
        for (j in 0 until 4 )
            if(buttonStates[i][j]){
                result = result or 1.shl(i * 4 + j)
            }

    return result
}