package eu.tutorial.moodle.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import eu.tutorial.moodle.data.IconDto
import eu.tutorial.moodle.data.TypeDto
import eu.tutorial.moodle.data.local.allEmojis
import eu.tutorial.moodle.data.local.emotionList
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentGray

@Composable
fun IconsComponent(
    iconList: List<IconDto> = emptyList(),
    typeList: List<TypeDto> = emotionList
) {
    val emojis: List<String> = iconList.map { it.iconDescription }

    if (iconList.isNotEmpty()) {
        Log.d("list", iconList.toString())
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if (emojis.isNotEmpty())
            DetailCard(
                emojis = emojis,
                typeList = typeList,
            )
        else
            EmptyCard()
    }
}

@Composable
fun DetailCard(
    emojis: List<String>,
    typeList: List<TypeDto> = emotionList
) {
    Box(
        modifier = Modifier
            .height(214.dp)
            .fillMaxWidth()
            .background(containerGray, shape = RoundedCornerShape(size = 18.dp))
            .padding(26.dp, 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(6.dp),// 가로
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            items(emojis) { item ->
                val type = typeList.find { it.typeDes == item }?.iconId

                allEmojis[type]?.let {
                    AlignYourBodyElement(
                        drawable = it,
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyCard() {
    Column(
        modifier = Modifier
            .height(78.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(containerGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "오늘 기록한 아이콘이 없어요",
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = contentGray
            ),
        )
    }
}