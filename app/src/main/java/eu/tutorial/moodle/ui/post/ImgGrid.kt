package eu.tutorial.moodle.ui.post

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import eu.tutorial.moodle.R

@Composable
fun ImgGrid(
    imgUri :  MutableState<Uri?>
) {
    // Boolean 타입의 변수 imageTy 선언
    var imageTy by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        // val 이기 때문에 .value로 접근해서 변경
        imgUri.value = uri
    }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color(color = 0Xff212122))
            .clip(shape = CircleShape.copy(all = CornerSize(45.dp)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 20.dp, bottom = 48.dp),
                text = "사진",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                textAlign = TextAlign.Center,
                color = Color(0XFFEDEDED)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 78.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (imgUri.value == null){
                    IconButton(
                        onClick = {
                            imageTy = true
                            launcher.launch("image/*")
                        },
                        modifier = Modifier
                            .padding(top = 36.dp)
                            .size(50.dp)
                            .clip(shape = CircleShape.copy(all = CornerSize(20.dp)))
                            .background(Color(0XFF363637))
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "add",
                            modifier = Modifier.size(24.dp),
                            tint = Color(0XFFEDEDED)
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = "하루를 요약하는 사진이 있나요?",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color(0XFFEDEDED)
                    )
                } else{
                    AsyncImage(
                        model = imgUri.value,
                        contentDescription = null,
                        modifier = Modifier
                            .wrapContentWidth(align = Alignment.Start)
                            .padding(3.dp)
                    )
//                    Log.d("save", imgUri.value.toString())
//                    Image(
//                        painter = rememberImagePainter(data = imgUri.value),
//                        contentDescription = null,
//                        modifier = Modifier
//                                .wrapContentWidth(align = Alignment.Start)
//                                .padding(3.dp)
//                    )
                }
            }
        }
    }
}