package eu.tutorial.moodle.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.local.allEmojis
import eu.tutorial.moodle.data.local.comments
import eu.tutorial.moodle.data.local.diaryText
import eu.tutorial.moodle.ui.comment.CommentScreen
import eu.tutorial.moodle.ui.component.AlignYourBodyElement
import eu.tutorial.moodle.ui.theme.poppins

@Composable
fun ViewScreen(
    showCommentScreen: Boolean,
    setShowCommentScreen: (Boolean) -> Unit,
    showViewScreen: () -> Unit,
    onCloseClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFF151515))
            .padding(start = 20.dp, end = 20.dp)
            .then(Modifier.verticalScroll(rememberScrollState()))
    ) {
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(80.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "2023년 8월",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Color(0XFFDFDFDF)
                )
                Text(
                    text = "16일 수요일",
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Color(0XFFDFDFDF)
                )
            }
            IconButton(onClick = showViewScreen) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "close",
                    tint = Color(0XFFDFDFDF),
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Text(
            text = "감정",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            color = Color(0XFFDFDFDF),
            modifier = Modifier.padding(top = 24.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            repeat(1) { rowIndex ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(2) { columnIndex ->
                        Image(
                            painter = painterResource(id = R.drawable.angry),
                            contentDescription = "angry",
                            modifier = Modifier
                                .padding(5.dp)
                                .size(58.dp)
                        )
                    }
                }
            }
        }


        Text(
            text = "활동",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            color = Color(0XFFDFDFDF),
            modifier = Modifier.padding(top = 24.dp)
        )



        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            repeat(3) { rowIndex ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(5) { columnIndex ->
                        if (rowIndex * 5 + columnIndex < 14) { // 13개까지만 버튼 생성
                            Button(
                                onClick = {
                                    // 버튼 클릭 시 수행할 작업
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF363637)
                                ),
                                modifier = Modifier
                                    .padding(5.dp)
                                    .size(60.dp),
                                shape = RoundedCornerShape(20.dp)
                            ) {
                                Text(
                                    text = "🏠",
                                    fontSize = 30.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }


        Text(
            text = "기록",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            color = Color(0XFFDFDFDF),
            modifier = Modifier.padding(top = 24.dp)
        )

        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .clip(shape = RoundedCornerShape(18.dp))
                .background(Color(0XFF2A292B))
                .fillMaxWidth(),
        ){
            Text(
                text = diaryText,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = Color(0XFFDFDFDF),
                modifier = Modifier.padding(16.dp, 20.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .clip(shape = RoundedCornerShape(18.dp))
                .background(Color(0XFF2A292B))
                .fillMaxWidth()
                .height(300.dp),
        ) {

        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { setShowCommentScreen(true) },
                modifier = Modifier
                    .defaultMinSize(
                        minWidth = 1.dp,
                        minHeight = 31.dp
                    )
                    .padding(top = 24.dp, bottom = 57.dp),
                contentPadding = PaddingValues(top = 5.dp, bottom = 5.dp, start = 17.dp, end = 17.dp),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(1.dp, Color(0XFFDFDFDF)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text(
                    text = "%d Comments".format(1),
                    color = Color(0XFFDFDFDF),
                    fontFamily = poppins
                )
            }
        }

    }
    Box(
        modifier = Modifier
            .background(color = Color(0X00000000))
    ){
        androidx.compose.animation.AnimatedVisibility(
            visible = showCommentScreen,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it }),
//            modifier = Modifier
//                .align(Alignment.BottomCenter) // 이 align 은 box scope 이기 때문에 안에서 써야 한다.
        ) {
            CommentScreen(
                modifier = Modifier
//                    .clip(shape = RoundedCornerShape(32.dp)) // 이게 먼저 와야함
                    .background(color = Color.Black.copy(alpha = 0.3f)),
                onCloseClick = {
                    setShowCommentScreen(false)
                },
                comments = comments
            )
        }
    }
}

