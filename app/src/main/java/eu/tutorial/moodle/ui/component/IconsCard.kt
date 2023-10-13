package eu.tutorial.moodle.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.local.Emoji
import eu.tutorial.moodle.data.local.allEmojis
import eu.tutorial.moodle.ui.theme.poppins


@Composable
fun IconsComponent(
    emojis : List<Emoji> = emptyList()
){

    // TODO 이 부분 코드 개선.. value를 빼서 Composable 줄이는 방향
    if(emojis.isNotEmpty()){
        Card(
            modifier = Modifier
                .height(186.dp)        // TODO : item 개수에 따라서 height 값을 조절 해야 합니다. 1 - 4 : 104 / 5 - 8 : 186 / 9 - 12 : 268
                .padding(start = 12.dp, end = 12.dp),
            shape = RoundedCornerShape(32.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(color = Color(0XD9D9D9D9)),
                contentAlignment = Alignment.Center,
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    contentPadding = PaddingValues(horizontal = 16.5.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),// 가로
                    verticalArrangement = Arrangement.spacedBy(10.dp)
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
    } else{
        Column(
            modifier = Modifier
                .padding(start = 50.dp, end = 50.dp)
                .fillMaxWidth()
        ) {
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Icons",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Color(0XFFDFDFDF)
                )
                Text(
                    text = "더보기",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0XFF7E7E7E)
                )
            }
            
            Card(
                shape = RoundedCornerShape(18.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(192.dp)
                        .fillMaxWidth()
                        .background(color = Color(0XFF212122)),
                    contentAlignment = Alignment.Center,
                ){
                    Text(
                        text = "No Icons",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = Color(0XFF7E7E7E)
                        ),
                    )
                }
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

            )
        }
    }
}

