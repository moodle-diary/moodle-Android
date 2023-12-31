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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorial.moodle.data.RateDays
import eu.tutorial.moodle.data.TypeDto
import eu.tutorial.moodle.ui.AppViewModelProvider
import eu.tutorial.moodle.ui.chart.component.RankCard
import eu.tutorial.moodle.ui.chart.component.RateComponent
import eu.tutorial.moodle.ui.chart.component.TimeChartCard
import eu.tutorial.moodle.ui.theme.backgroundGray
import eu.tutorial.moodle.ui.theme.greatGreen
import eu.tutorial.moodle.ui.theme.mainOrange
import eu.tutorial.moodle.ui.theme.nothingGray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChartScreen(
    innerPaddingValues: PaddingValues = PaddingValues(0.dp),
    viewModel: ChartViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavController,
) {
    val scrollState = rememberScrollState()
    val selectedDate by viewModel.selectedDate

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

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(selectedDate) {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                // 데이터베이스 쿼리를 비동기적으로 수행
                viewModel.getEmotionList(selectedDate.toString())
                viewModel.getActList(selectedDate.toString())
                viewModel.getPlaceList(selectedDate.toString())
                viewModel.getCauseTypes()
                viewModel.getPlaceTypes()
                viewModel.getGreatDays(selectedDate.toString())
                viewModel.getRemindDays(selectedDate.toString())
                viewModel.getTimeList(selectedDate.toString())
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGray)
            .padding(innerPaddingValues)
            .verticalScroll(scrollState)
    ) {
        TimeChartCard(
            timeList = viewModel.timeList
        )

        RankCard(
            rankState = viewModel.emotionList,
            category = "가장 힘들게 한 원인 감정"
        )
        RankCard(
            rankState = viewModel.causeList,
            typeState = causeType,
            category = "가장 힘들게 한 원인"
        )
        RankCard(
            rankState = viewModel.placeList,
            typeState = placeType,
            category = "가장 힘들었던 장소"
        )

        RateComponent(
            listOf(
                RateDays(viewModel.remindDays - viewModel.greatDays, mainOrange),
                RateDays(viewModel.greatDays, greatGreen),
                RateDays(selectedDate.lengthOfMonth() - viewModel.remindDays, nothingGray)
            )
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
