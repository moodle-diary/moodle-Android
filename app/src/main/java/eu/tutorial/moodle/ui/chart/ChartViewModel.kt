package eu.tutorial.moodle.ui.chart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.tutorial.moodle.data.CauseGrade
import eu.tutorial.moodle.data.DescriptionDto
import eu.tutorial.moodle.data.DiaryRepository
import eu.tutorial.moodle.data.PlaceGrade

class ChartViewModel (private val diaryRepository: DiaryRepository) : ViewModel() {

    var causeList by mutableStateOf(emptyList<DescriptionDto>())
    var placeList by mutableStateOf(emptyList<DescriptionDto>())
    var peopleList by mutableStateOf(emptyList<DescriptionDto>())
    var foodList by mutableStateOf(emptyList<DescriptionDto>())

    fun getActList(targetMonth : String){
        causeList = diaryRepository.getCauseGrade("$targetMonth%").map { it.toDesDto() }
    }
    fun getPlaceList(targetMonth : String) {
        placeList = diaryRepository.getPlaceGrade("$targetMonth%").map { it.toDesDto() }
    }
}

fun CauseGrade.toDesDto() : DescriptionDto = DescriptionDto(
    description = cause,
    cnt = cnt,
)

fun PlaceGrade.toDesDto() : DescriptionDto = DescriptionDto(
    description = placeDescription,
    cnt = cnt,
)