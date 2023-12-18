package eu.tutorial.moodle.ui.chart.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.RateDays
import eu.tutorial.moodle.data.local.rateCategory
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.greatGreen
import eu.tutorial.moodle.ui.theme.mainOrange
import eu.tutorial.moodle.ui.theme.nothingGray

@Composable
fun RateComponent(
    rateDays: List<RateDays>
) {
    Column(
        modifier = Modifier
            .padding(12.dp, 8.dp)
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 10.dp)
    ) {
        Text(
            text = " •  비중",
            fontSize = 16.sp,
            color = mainOrange,
            fontFamily = FontFamily(Font(R.font.poppins_bold))
        )
        Column(
            modifier = Modifier
                .padding(12.dp, 8.dp)
                .height(350.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(containerGray)
                .padding(top = 16.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 18.dp),
                columns = GridCells.Fixed(7),
            ) {
                rateDays.map { rate ->
                    items(rate.days) {
                        RateBoxItem(
                            boxColor = rate.color
                        )
                    }
                }
            }

            rateCategory.map {
                RateCategoryItem(
                    it.textValue,
                    it.boxColor
                )
            }
        }
    }
}


@Preview
@Composable
fun RateComponentPreview() {
    RateComponent(
        listOf(
            RateDays(17, mainOrange),
            RateDays(8, greatGreen),
            RateDays(6, nothingGray)
        )
    )
}
