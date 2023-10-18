package eu.tutorial.moodle.ui.chart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.tutorial.moodle.data.ActGrade
import eu.tutorial.moodle.data.DescriptionDto
import eu.tutorial.moodle.data.DiaryRepository
import eu.tutorial.moodle.data.FoodGrade
import eu.tutorial.moodle.data.People
import eu.tutorial.moodle.data.PeopleGrade
import eu.tutorial.moodle.data.PlaceGrade

class ChartViewModel (private val diaryRepository: DiaryRepository) : ViewModel() {

    var activityList by mutableStateOf(emptyList<DescriptionDto>())
    var placeList by mutableStateOf(emptyList<DescriptionDto>())
    var peopleList by mutableStateOf(emptyList<DescriptionDto>())
    var foodList by mutableStateOf(emptyList<DescriptionDto>())

    fun getActList(targetMonth : String){
        activityList = diaryRepository.getActivityGrade("$targetMonth%").map { it.toDesDto() }
    }
    fun getPlaceList(targetMonth : String) {
        placeList = diaryRepository.getPlaceGrade("$targetMonth%").map { it.toDesDto() }
    }
    fun getPeopleList(targetMonth : String) {
        peopleList = diaryRepository.getPeopleGrade("$targetMonth%").map { it.toDesDto() }
    }
    fun getFoodList(targetMonth : String) {
        foodList = diaryRepository.getFoodGrade("$targetMonth%").map { it.toDesDto() }
    }
}

fun ActGrade.toDesDto() : DescriptionDto = DescriptionDto(
    description = activityDescription,
    cnt = cnt,
)

fun PlaceGrade.toDesDto() : DescriptionDto = DescriptionDto(
    description = placeDescription,
    cnt = cnt,
)
fun PeopleGrade.toDesDto() : DescriptionDto = DescriptionDto(
    description = peopleDescription,
    cnt = cnt,
)
fun FoodGrade.toDesDto() : DescriptionDto = DescriptionDto(
    description = foodDescription,
    cnt = cnt,
)