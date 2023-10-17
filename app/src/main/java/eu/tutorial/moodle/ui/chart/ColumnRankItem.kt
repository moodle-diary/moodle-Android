package eu.tutorial.moodle.ui.chart

import android.os.Build
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
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun ColumnRankItem() {

    val brush = Brush.horizontalGradient(listOf(Color(0xFF202020), Color(0xFFD1D1D1)))

    Column(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxWidth()
    ) {
        // 1
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
                    text = "슬픔",
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
                        .width(181.dp)
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
        // 2
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
                    text = "슬픔",
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
                        .width(161.dp)
                        .padding(end = 8.dp)
                        .clip(RoundedCornerShape(bottomEnd = 10.dp, topEnd = 10.dp))
                        .background(brush)
                )
                Text(
                    text = "20",
                    fontSize = 14.sp,
                    color = Color(0XFFEDEDED),
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
            }
        }
        // 3
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
                    text = "슬픔",
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
                        .width(81.dp)
                        .padding(end = 8.dp)
                        .clip(RoundedCornerShape(bottomEnd = 10.dp, topEnd = 10.dp))
                        .background(brush)
                )
                Text(
                    text = "10",
                    fontSize = 14.sp,
                    color = Color(0XFFEDEDED),
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
            }
        }
        // 4
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
                    text = "슬픔",
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
                        .width(121.dp)
                        .padding(end = 8.dp)
                        .clip(RoundedCornerShape(bottomEnd = 10.dp, topEnd = 10.dp))
                        .background(brush)
                )
                Text(
                    text = "14",
                    fontSize = 14.sp,
                    color = Color(0XFFEDEDED),
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
            }
        }
        // 5
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
                    text = "슬픔",
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
                        .width(41.dp)
                        .padding(end = 8.dp)
                        .clip(RoundedCornerShape(bottomEnd = 10.dp, topEnd = 10.dp))
                        .background(brush)
                )
                Text(
                    text = "4",
                    fontSize = 14.sp,
                    color = Color(0XFFEDEDED),
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
            }
        }
        // 6
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
                    text = "슬픔",
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
                        .width(181.dp)
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
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ColumnRankItemPreview(){
    ColumnRankItem()
}