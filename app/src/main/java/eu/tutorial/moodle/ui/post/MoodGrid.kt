package eu.tutorial.moodle.ui.post

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoodGrid(
    buttonStates :  SnapshotStateList<SnapshotStateList<Boolean>>,
    diaryUiState: DiaryUiState,
    onClick : (DiaryDetails) -> Unit
) {

    val data = listOf(
        "Furious", "Nervous", "Cheerful", "Ecstatic",
        "Worried", "Angry", "Happy", "Excited",
        "Lonely", "Sad", "At ease", "Content",
        "Hopeless", "Disappointed", "Calm", "Serene"
    )

    Box(
        modifier = Modifier
            .height(520.dp)
            .background(Color(color = 0XffEFEFEF))
            .clip(shape = CircleShape.copy(all = CornerSize(45.dp)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Emotions",
                fontSize = 24.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.padding(top = 4.dp, bottom = 28.dp),
                text = "What emotions have you felt?",
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                textAlign = TextAlign.Center
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
                                        currentDate = LocalDate.now().toString(),
                                        emotions = convertToBitMap(buttonStates)
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
                            fontWeight = FontWeight(400),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .width(65.dp)
                                .padding(top = 4.dp, bottom = 8.dp)
                        )
                    }
                }
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