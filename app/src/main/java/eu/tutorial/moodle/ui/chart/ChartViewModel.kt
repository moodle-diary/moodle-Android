package eu.tutorial.moodle.ui.chart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.tutorial.moodle.data.ActGrade
import eu.tutorial.moodle.data.DiaryRepository
import eu.tutorial.moodle.data.FoodGrade
import eu.tutorial.moodle.data.People
import eu.tutorial.moodle.data.PeopleGrade
import eu.tutorial.moodle.data.PlaceGrade

class ChartViewModel (private val diaryRepository: DiaryRepository) : ViewModel() {
    var activityList by mutableStateOf(emptyList<ActGrade>())
    var placeList by mutableStateOf(emptyList<PlaceGrade>())
    var peopleList by mutableStateOf(emptyList<PeopleGrade>())
    var foodList by mutableStateOf(emptyList<FoodGrade>())

    fun getActList(targetMonth : String){
        activityList = diaryRepository.getActivityGrade("$targetMonth%")
    }

    fun getPlaceList(targetMonth : String) {
        placeList = diaryRepository.getPlaceGrade("$targetMonth%")
    }

    fun getPeopleList(targetMonth : String) {
        peopleList = diaryRepository.getPeopleGrade("$targetMonth%")
    }

    fun getFoodList(targetMonth : String) {
        foodList = diaryRepository.getFoodGrade("$targetMonth%")
    }
}

