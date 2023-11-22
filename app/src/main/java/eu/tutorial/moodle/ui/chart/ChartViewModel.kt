package eu.tutorial.moodle.ui.chart

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tutorial.moodle.data.CauseGrade
import eu.tutorial.moodle.data.CauseTypeDto
import eu.tutorial.moodle.data.DescriptionDto
import eu.tutorial.moodle.data.DiaryRepository
import eu.tutorial.moodle.data.EmotionGrade
import eu.tutorial.moodle.data.PlaceGrade
import eu.tutorial.moodle.data.PlaceTypeDto
import eu.tutorial.moodle.data.TimeItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.YearMonth
import kotlin.properties.Delegates

class ChartViewModel(private val diaryRepository: DiaryRepository) : ViewModel() {

    var emotionList by mutableStateOf(emptyList<DescriptionDto>())

    var causeList by mutableStateOf(emptyList<DescriptionDto>())

    var placeList by mutableStateOf(emptyList<DescriptionDto>())

    var timeList by mutableStateOf(emptyList<TimeItem>())

    var causeTypes by mutableStateOf(emptyList<CauseTypeDto>())
        private set

    var placesTypes by mutableStateOf(emptyList<PlaceTypeDto>())
        private set

    var greatDays = 0

    private val _selectedDate = mutableStateOf(YearMonth.now())
    val selectedDate: State<YearMonth> = _selectedDate
    fun onDateSelect(selected: YearMonth) {
        _selectedDate.value = selected
        // 선택된 날짜가 변경될 때 다른 함수들도 호출할 수 있음
    }

    fun getCauseTypes() {
        causeTypes = diaryRepository.getCauseTypes()
    }

    fun getPlaceTypes() {
        placesTypes = diaryRepository.getPlaceTypes()
    }

    fun getGreatDays(targetMonth: String) {
        greatDays = diaryRepository.getGreatDays("$targetMonth%")
    }

    fun getEmotionList(targetMonth: String) {

        emotionList = diaryRepository.getEmotionGrade("$targetMonth%").map { it.toDesDto() }
    }

    fun getActList(targetMonth: String) {
        causeList = diaryRepository.getCauseGrade("$targetMonth%").map { it.toDesDto() }
    }

    fun getPlaceList(targetMonth: String) {
        placeList = diaryRepository.getPlaceGrade("$targetMonth%").map { it.toDesDto() }
    }

    fun getTimeList(targetMonth: String) {
        timeList = diaryRepository.getHourRate("$targetMonth%")
    }

}

fun EmotionGrade.toDesDto(): DescriptionDto = DescriptionDto(
    description = emotion,
    cnt = cnt
)

fun CauseGrade.toDesDto(): DescriptionDto = DescriptionDto(
    description = cause,
    cnt = cnt,
)

fun PlaceGrade.toDesDto(): DescriptionDto = DescriptionDto(
    description = placeDescription,
    cnt = cnt,
)