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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R


@Composable
fun PhotosComponent(
    photo : String = ""
){
    Card(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp),
        shape = RoundedCornerShape(18.dp)
    ) {
        if(photo != ""){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(color = Color(0XFF2A292B)),
            ){
                // 사진 불러오기
            }
        } else{
            Box(
                modifier = Modifier
                    .height(104.dp)
                    .fillMaxWidth()
                    .background(color = Color(0XFF2A292B)),
                contentAlignment = Alignment.Center,
            ){
                Text(
                    text = "No Photos",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color(0XFFDFDFDF)
                    ),
                )
            }
        }
    }

}

//@Preview(
//    showSystemUi = true,
//    showBackground = true
//)
//@Composable
//fun EmotionChartPreview(){
//    NotesComponent(exist = true)
//}