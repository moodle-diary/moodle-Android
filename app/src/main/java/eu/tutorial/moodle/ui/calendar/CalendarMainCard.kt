package eu.tutorial.moodle.ui.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import eu.tutorial.moodle.ui.home.DetailHomeScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarMainCard(
    innerPadding : PaddingValues
){
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp),
            shape = RoundedCornerShape(32.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(359.dp)
                    .background(Color(0XFFD9D9D9)),
                contentAlignment = Alignment.Center
            ) {
                HorizontalCalendar()
            }
        }

        Spacer(modifier = Modifier.size(11.dp))

        Card(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp),
            shape = RoundedCornerShape(32.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(247.dp)
                    .background(Color(0XFFEFEFEF)),
                contentAlignment = Alignment.Center
            ) {
                EmotionChart()
            }
        }
    }

    val isVisible by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
    ){
        AnimatedVisibility(
            visible = isVisible,
            modifier = Modifier
                .align(Alignment.BottomCenter) // 이 align 은 box scope 이기 때문에 안에서 써야 한다.
        ) {
            DetailHomeScreen(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(32.dp)) // 이게 먼저 와야함
                    .background(color = Color(0XFF9D9D9D))
            )
        }
    }

}