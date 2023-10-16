package eu.tutorial.moodle.ui.component

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.ImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.ImgDto


@Composable
fun PhotosComponent(
    imgList: List<ImgDto>
){

    Card(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp),
        shape = RoundedCornerShape(18.dp)
    ) {
        if(imgList.isNotEmpty()){

            val imgUri = imgList[0].imgUri

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(color = Color(0XFF2A292B)),
            ){

                val painter = rememberAsyncImagePainter(model = imgUri)
                // 이미지가 로드될 때까지 대기
                LaunchedEffect(painter) {
                    while (painter.state is AsyncImagePainter.State.Loading) {
                        Log.d("state", painter.state.toString())
                        // 이미지 로딩 중
                    }
                }

                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize() // 이미지를 화면 크기로 채우도록 설정
                )
//                val painter = rememberAsyncImagePainter(model = Uri.parse(imgUri))
//                Image(
//                    painter = painter,
//                    contentDescription = null
//                )

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
