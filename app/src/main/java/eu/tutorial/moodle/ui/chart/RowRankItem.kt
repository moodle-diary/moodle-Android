package eu.tutorial.moodle.ui.chart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R

@Composable
fun RowRankItem() {
    Row(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxWidth()
            .height(104.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 1
        Column(
            modifier = Modifier
                .width(60.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Pets,
                contentDescription = "icon",
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = 12.dp)
            )
            Text(
                text = "슬픔",
                fontSize = 12.sp,
                color = Color(0XFFEDEDED),
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
            Text(
                text = "24번",
                fontSize = 12.sp,
                color = Color(0XFFEDEDED).copy(alpha = 0.5f),
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        }
        // 2
        Column(
            modifier = Modifier
                .width(60.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Pets,
                contentDescription = "icon",
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = 12.dp)
            )
            Text(
                text = "슬픔",
                fontSize = 12.sp,
                color = Color(0XFFEDEDED),
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
            Text(
                text = "24번",
                fontSize = 12.sp,
                color = Color(0XFFEDEDED).copy(alpha = 0.5f),
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        }
        // 3
        Column(
            modifier = Modifier
                .width(60.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Pets,
                contentDescription = "icon",
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = 12.dp)
            )
            Text(
                text = "슬픔",
                fontSize = 12.sp,
                color = Color(0XFFEDEDED),
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
            Text(
                text = "24번",
                fontSize = 12.sp,
                color = Color(0XFFEDEDED).copy(alpha = 0.5f),
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        }
        // 4
        Column(
            modifier = Modifier
                .width(60.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Pets,
                contentDescription = "icon",
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = 12.dp)
            )
            Text(
                text = "슬픔",
                fontSize = 12.sp,
                color = Color(0XFFEDEDED),
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
            Text(
                text = "24번",
                fontSize = 12.sp,
                color = Color(0XFFEDEDED).copy(alpha = 0.5f),
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        }
        // 5
        Column(
            modifier = Modifier
                .width(60.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Pets,
                contentDescription = "icon",
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = 12.dp)
            )
            Text(
                text = "슬픔",
                fontSize = 12.sp,
                color = Color(0XFFEDEDED),
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
            Text(
                text = "24번",
                fontSize = 12.sp,
                color = Color(0XFFEDEDED).copy(alpha = 0.5f),
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        }
    }
}