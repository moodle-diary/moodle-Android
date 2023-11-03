package eu.tutorial.moodle.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.ActivityDto
import eu.tutorial.moodle.data.FoodDto
import eu.tutorial.moodle.data.PeopleDto
import eu.tutorial.moodle.data.PlaceDto
import eu.tutorial.moodle.data.local.allEmojis

@Composable
fun IconsComponent(
    modifier: Modifier = Modifier,
    activityList : List<ActivityDto> = emptyList(),
    placeList : List<PlaceDto> = emptyList(),
    peopleList: List<PeopleDto> = emptyList(),
    foodList : List<FoodDto> = emptyList(),
){
    val emojis : List<String> = activityList.map { it.activityDescription } +
            placeList.map { it.placeDescription } +
            peopleList.map { it.peopleDescription } +
            foodList.map { it.foodDescription }
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "오늘의 활동",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                color = Color(0XFFDFDFDF)
            )
            Text(
                text = "모두 보기",
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = Color(0XFF7E7E7E)
            )
        }

        if(emojis.isNotEmpty())
            DetailCard(emojis = emojis)
        else
            EmptyCard()
    }
}

@Composable
fun DetailCard(
    emojis : List<String>
){
    Box(
        modifier = Modifier
            .height(192.dp)
            .background(color = colorResource(R.color.backGround)),
        contentAlignment = Alignment.Center,
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(6.dp),// 가로
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(emojis) { item ->
                allEmojis[item]?.let {
                    AlignYourBodyElement(
                        drawable = it,
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyCard(){
    Column(
        modifier = Modifier
            .height(192.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(color = Color(0XFF212122)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "오늘 기록한 아이콘이 없어요",
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = Color(0XFF686868)
            ),
        )
    }
}