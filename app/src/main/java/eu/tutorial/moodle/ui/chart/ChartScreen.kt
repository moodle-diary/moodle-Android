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
import eu.tutorial.moodle.data.TypeDto
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

    val emotionState = viewModel.emotionList
    val causeState = viewModel.causeList
    val placeState = viewModel.placeList

    val causeType = viewModel.causeTypes.map {
        TypeDto(
            iconId = it.iconId,
            typeDes = it.causeType
        )
    }
    val placeType = viewModel.placesTypes.map {
        TypeDto(
            iconId = it.iconId,
            typeDes = it.placeType,
        )
    }

    val greatDay = viewModel.greatDays

    val coroutineScope = rememberCoroutineScope()

    // TODO : 날짜 변경 필요 몇 월인지를 받아온다
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                // 데이터베이스 쿼리를 비동기적으로 수행
                viewModel.getEmotionList("2023-11")
                viewModel.getActList("2023-11")
                viewModel.getPlaceList("2023-11")
                viewModel.getCauseTypes()
                viewModel.getPlaceTypes()

                viewModel.getGreatDays("2023-11")
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
//        TopRankCard(
//            maxList = maxList.toList()
//        )
        RankCard(
            rankState = emotionState,
            category = "감정 순위"
        )
        RankCard(
            rankState = causeState,
            typeState = causeType,
            category = "원인 순위"
        )
        RankCard(
            rankState = placeState,
            typeState = placeType,
            category = "장소 순위"
        )
    }

    // TODO: impl great day rate view with value greatDays
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun ChartScreenPreview() {
    val navController = rememberNavController()
    ChartScreen(navController = navController)
}