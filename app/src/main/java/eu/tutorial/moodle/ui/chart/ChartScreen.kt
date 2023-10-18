package eu.tutorial.moodle.ui.chart

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorial.moodle.ui.post.PostEmotionScreen

@Composable
fun ChartScreen(
    innerPaddingValues: PaddingValues = PaddingValues(0.dp),
    navController: NavController
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFF151515))
            .padding(innerPaddingValues)
            .verticalScroll(scrollState)
    ) {

        TopRankCard()

        EmotionRankCard()
        ActivityRankCard()
        PlaceRankCard()
        PeopleRankCard()
        FoodRankCard()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun ChartScreenPreview() {
    val navController = rememberNavController()
    ChartScreen(navController = navController)
}