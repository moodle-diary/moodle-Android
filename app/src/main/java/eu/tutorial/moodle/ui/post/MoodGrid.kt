package eu.tutorial.moodle.ui.post

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.local.allEmojis
import eu.tutorial.moodle.data.local.emotionData
import eu.tutorial.moodle.ui.post.component.GoodDialog
import eu.tutorial.moodle.ui.theme.backgroundGray
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.contentGray
import eu.tutorial.moodle.ui.theme.poppins

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoodGrid(
    buttonStates: SnapshotStateList<SnapshotStateList<Boolean>>,
    navController: NavController,
    viewModel: PostViewModel,
) {

    var showDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(containerGray)
            .clip(RoundedCornerShape(18.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 32.dp, bottom = 28.dp),
            text = "어떤 감정이었나요?",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            textAlign = TextAlign.Center,
            color = contentBlack
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.padding(25.dp, 0.dp)
            //modifier = Modifier.padding(top = 108.dp, start = 20.dp)
        ) {
            itemsIndexed(emotionData) { index, item ->
                // 각 데이터 아이템을 Row 컴포넌트 내에서 텍스트와 버튼으로 구성
                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    val isClicked = buttonStates[index / 4][index % 4]
                    val backgroundColor = if (isClicked) {
                        Color.Transparent //클릭된 경우
                    } else {
                        Color.Transparent //클릭되지 않은 경우
                    }

                    IconButton(
                        onClick = {
                            buttonStates[index / 4][index % 4] = !isClicked
                        },
                        modifier = Modifier
                            .size(55.dp)
                            .background(backgroundColor) // 배경색 설정
                            .clip(RoundedCornerShape(if (isClicked) 25.dp else 0.dp))
                    ) {
                        allEmojis[item]?.let { painterResource(it) }?.let {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(55.dp)
                                        .background(containerGray.copy(alpha = .5f))
                                )

                                val clickedZIndex = if (isClicked) -1f else 1f

                                Image(
                                    painter = it,
                                    contentDescription = null,
                                    modifier = Modifier.zIndex(clickedZIndex)
                                )
                            }
                        }
                    }

                    Text(
                        text = item,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, bottom = 8.dp),
                        color = contentBlack
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
                border = BorderStroke(
                    2.dp,
                    Brush.linearGradient(listOf(Color(0XFFFFCF25), Color(0XFFF198FF)))
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = backgroundGray,
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
                    color = contentGray
                )
            }
        }

        GoodDialog(
            navController = navController,
            visibility = showDialog.value,
            viewModel = viewModel
        ) { dialogVisible -> showDialog.value = dialogVisible }
    }
}
