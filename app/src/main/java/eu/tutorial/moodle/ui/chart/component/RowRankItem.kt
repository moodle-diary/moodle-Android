package eu.tutorial.moodle.ui.chart.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.DescriptionDto
import eu.tutorial.moodle.data.TypeDto
import eu.tutorial.moodle.data.local.allEmojis
import eu.tutorial.moodle.data.local.causeEmojiList
import eu.tutorial.moodle.data.local.emotionList
import eu.tutorial.moodle.data.local.placeEmojiList
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.contentGray
import kotlin.math.min

@Composable
fun RowRankItem(
    listState: List<DescriptionDto>,
    typeState: List<TypeDto> = emotionList,
) {
    Row(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxWidth()
            .height(104.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val maxItemsToShow = 3
        var rank = 1
        repeat(min(listState.size, maxItemsToShow)) { it ->

            val des = listState[it].description
            val type = typeState.find { it.typeDes == des }
            Spacer(modifier = Modifier.width(16.dp))
            if (type != null) {
                RankItem(
                    description = des,
                    cnt = listState[it].cnt,
                    iconId = type.iconId,
                    rank = rank
                )
                rank += 1
            }
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Composable
fun RankItem(
    description: String,
    cnt: Int,
    iconId: String,
    rank: Int
) {
    val allEmojiMap = allEmojis + causeEmojiList + placeEmojiList
    Column(
        modifier = Modifier
            .width(60.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        allEmojiMap[iconId]?.let { painterResource(id = it) }?.let {
            Image(
                painter = it,
                contentDescription = "icon",
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = 12.dp)
            )
        }

        Text(
            text = description,
            fontSize = 12.sp,
            color = contentBlack,
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        )
        Text(
            text = "${rank}위", // cnt.toString() + "번",
            fontSize = 12.sp,
            color = contentGray,
            fontFamily = FontFamily(Font(R.font.poppins_bold))
        )
    }
}
