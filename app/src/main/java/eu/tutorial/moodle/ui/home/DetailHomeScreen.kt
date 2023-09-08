package eu.tutorial.moodle.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TripOrigin
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.Dp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.local.allEmojis
import eu.tutorial.moodle.data.local.diaryText


@Composable
fun DetailHomeScreen(
    innerPaddingValues: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
){
    val verticalScrollState = rememberScrollState()

    Column(
        modifier = modifier
            .padding(innerPaddingValues)
            .verticalScroll(verticalScrollState)
    ) {

//        Spacer(
//            modifier = Modifier.size(400.dp)
//        )

        Card(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp),
            shape = RoundedCornerShape(32.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .background(color = Color(0XEFEFEFEF)),
                contentAlignment = Alignment.Center
            ){
            }
        }

        Spacer(modifier = Modifier.size(12.dp)) // Spacer 적용시 padding 보다 size 적ㅖ

        Card(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp),
            shape = RoundedCornerShape(32.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(104.dp)
                    .fillMaxWidth()
                    .background(color = Color(0XD9D9D9D9)),
                contentAlignment = Alignment.Center,
            ){
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp), // // content의 패딩
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier
                ){
                    items(allEmojis){ item ->
                        AlignYourBodyElement(
                            drawable = item.emojiPaintId,
                            text = item.emojiDescriptionId
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.size(12.dp))

        Card(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp),
            shape = RoundedCornerShape(32.dp)
        ) {
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
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontSize = 16.sp
                    )

                )
            }
        }
    }
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally, // 가운데 정렬
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(shape = RoundedCornerShape(20.dp))
            ,
            contentAlignment = Alignment.Center,
        ){
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(0XFFEFEFEF))
            )
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop, // 동그랗게 만듬
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun detailScreenPreview(){
    DetailHomeScreen()
}