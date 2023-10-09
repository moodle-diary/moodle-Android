package eu.tutorial.moodle.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.local.diaryText
import eu.tutorial.moodle.ui.comment.CommentBox

@Composable
fun NotesComponent(
    exist : Boolean
){
    Card( // TODO 이 부분 코드 개선.. value를 빼서 Composable 줄이는 방향
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp),
        shape = RoundedCornerShape(32.dp)
    ) {
        if(exist){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0XD9D9D9D9)),
            ){
                Text(
                    text = diaryText,
                    modifier = Modifier
                        .padding(start = 18.dp, top = 32.dp, bottom = 32.dp, end = 18.dp),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 16.sp
                    )

                )
            }
        }
        else{
            Box(
                modifier = Modifier
                    .height(104.dp)
                    .fillMaxWidth()
                    .background(color = Color(0XD9D9D9D9)),
                contentAlignment = Alignment.Center,
            ){
                Text(
                    text = "Notes",
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        color = Color(0XFFFFFFFF)
                    ),
                )
            }
        }
    }

}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun EmotionChartPreview(){
    NotesComponent(exist = true)
}