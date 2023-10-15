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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter

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
            .height(520.dp)
            .background(Color(color = 0XffEFEFEF))
            .clip(shape = CircleShape.copy(all = CornerSize(45.dp)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 20.dp, bottom = 28.dp),
                text = "Photos",
                fontSize = 24.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center
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
                            .size(50.dp)
                            .clip(shape = CircleShape.copy(all = CornerSize(20.dp)))
                            .background(Color(0XFFFFFFFF))
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "add",
                            modifier = Modifier.size(24.dp),
                            tint = Color(0XFF000000)
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = "Any photos",
                        textAlign = TextAlign.Center
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