package eu.tutorial.moodle.ui.chart

import android.os.Build
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorial.moodle.data.DescriptionDto
import eu.tutorial.moodle.ui.AppViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withContext

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChartScreen(
    innerPaddingValues: PaddingValues = PaddingValues(0.dp),
    viewModel: ChartViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavController,
) {
    val scrollState = rememberScrollState()
    
    val causeState = viewModel.causeList
    val placeState = viewModel.placeList
    val peopleState = viewModel.peopleList
    val foodState = viewModel.foodList

    val emptyDto = DescriptionDto(
        description = "",
        cnt = 0
    )

    val maxList : ArrayList<DescriptionDto> = arrayListOf(emptyDto, emptyDto, emptyDto, emptyDto)
    val coroutineScope = rememberCoroutineScope()

    // TODO : 날짜 변경 필요
    LaunchedEffect(Unit){
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                // 데이터베이스 쿼리를 비동기적으로 수행
                viewModel.getActList("2023-10")
                viewModel.getPlaceList("2023-10")
            }
        }

    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFF151515))
            .padding(innerPaddingValues)
            .verticalScroll(scrollState)
    ) {

        TopRankCard(
            maxList = maxList.toList()
        )

        EmotionRankCard(
            causeState = causeState
        )
        ActivityRankCard(
            causeState = causeState
        )
        PlaceRankCard(
            placeState = placeState
        )
        PeopleRankCard(
            peopleState = peopleState
        )
        FoodRankCard(
            foodState = foodState
        )

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun ChartScreenPreview() {
    val navController = rememberNavController()
    ChartScreen(navController = navController)
}