package eu.tutorial.moodle.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.local.allEmojis
import eu.tutorial.moodle.data.local.diaryText


@Composable
fun DetailHomeScreen(
    modifier: Modifier = Modifier,
    innerPaddingValues: PaddingValues = PaddingValues(0.dp),
){
    val verticalScrollState = rememberScrollState()

    Column(
        modifier = modifier
            .padding(innerPaddingValues)
            .verticalScroll(verticalScrollState)
    ) {

        Spacer(
            modifier = Modifier.size(50.dp)
        )

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
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
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

            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun DetailScreenPreview(){
    DetailHomeScreen()
}