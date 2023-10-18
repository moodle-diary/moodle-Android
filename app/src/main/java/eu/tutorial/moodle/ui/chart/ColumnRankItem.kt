package eu.tutorial.moodle.ui.chart

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.ActGrade
import eu.tutorial.moodle.data.DescriptionDto

@Composable
fun ColumnRankItem(
    listState : List<DescriptionDto>
) {

    val brush = Brush.horizontalGradient(
        listOf(Color(0xFF202020), Color(0xFFD1D1D1))
    )

    val totalSize = getTotalSize(listState)

    Column(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxWidth()
    ) {
        repeat( listState.size ) {
            ChartBar(
                brush = brush,
                description = listState[it].description,
                chartSize = 200 * listState[it].cnt / totalSize
            )
        }
        Log.d("listState", listState.toString())
    }
}

@Composable
fun ChartBar(
    brush: Brush,
    description: String,
    chartSize : Int,
){
    Row(
        modifier = Modifier
            .height(90.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(end = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Pets,
                contentDescription = "icon",
                modifier = Modifier
                    .size(50.dp)
                    .padding(bottom = 4.dp)
            )
            Text(
                text = description,
                fontSize = 12.sp,
                color = Color(0XFFEDEDED),
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(16.dp)
                    .width(chartSize.dp)
                    .padding(end = 8.dp)
                    .clip(RoundedCornerShape(bottomEnd = 10.dp, topEnd = 10.dp))
                    .background(brush)
            )
            Text(
                text = "24",
                fontSize = 14.sp,
                color = Color(0XFFEDEDED),
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        }
    }
}

fun getTotalSize(
    list: List<DescriptionDto>
) : Int{
    var result = 0
    for ( i in list.indices){
        result += list[i].cnt
    }
    return result
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ColumnRankItemPreview(){
//    ColumnRankItem(
//
//    )
}