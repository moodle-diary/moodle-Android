package eu.tutorial.moodle.ui.post

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoodGrid(
    buttonStates: SnapshotStateList<SnapshotStateList<Boolean>>,
    diaryUiState: DiaryUiState,
    onClick: (DiaryDetails) -> Unit
) {

    val data = listOf(
        "화남", "긴장됨", "행복함", "기쁨",
        "걱정됨", "불편함", "편안함", "신남",
        "외로음", "슬픔", "차분함", "만족스러움",
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
                //modifier = Modifier.padding(top = 108.dp, start = 20.dp)
            ) {
                itemsIndexed(data) { index, item ->
                    // 각 데이터 아이템을 Row 컴포넌트 내에서 텍스트와 버튼으로 구성
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
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
                                        currentDate = LocalDate.now().toString()
                                    )
                                )
                            },
                            modifier = Modifier
                                .size(60.dp)
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
                                .width(65.dp)
                                .padding(top = 4.dp, bottom = 8.dp),
                            color = Color(0XFFEDEDED)
                        )
                    }
                }
            }
        }
    }
}
