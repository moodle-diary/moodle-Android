package eu.tutorial.moodle.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.tutorial.moodle.data.ActivityDto
import eu.tutorial.moodle.data.Diary
import eu.tutorial.moodle.data.DiaryDto
import eu.tutorial.moodle.data.DiaryRepository
import eu.tutorial.moodle.data.FoodDto
import eu.tutorial.moodle.data.ImgDto
import eu.tutorial.moodle.data.PeopleDto
import eu.tutorial.moodle.data.PlaceDto

class HomeViewModel( private val diaryRepository: DiaryRepository ) : ViewModel(){
    var diaryUiState by mutableStateOf(emptyList<DiaryDto>())
        private set

    var activitiesUiState by mutableStateOf(emptyList<ActivityDto>())
        private set

    var placesUiState by mutableStateOf(emptyList<PlaceDto>())
        private set

    var peopleUiState by mutableStateOf(emptyList<PeopleDto>())
        private set

    var foodsUiState by mutableStateOf(emptyList<FoodDto>())
        private set

    var imgUiState by mutableStateOf(emptyList<ImgDto>())
        private set
    fun getDiaries(currentDate : String) {
        diaryUiState = diaryRepository.getDiaries(currentDate = currentDate)
    }
    fun getActivities(currentDate : String) {
        activitiesUiState = diaryRepository.getActivities(currentDate = currentDate)
    }
    fun getPlaces(currentDate : String) {
        placesUiState = diaryRepository.getPlaces(currentDate = currentDate)
    }
    fun getPeople(currentDate : String) {
        peopleUiState = diaryRepository.getPeople(currentDate = currentDate)
    }
    fun getFoods(currentDate : String) {
        foodsUiState = diaryRepository.getFood(currentDate = currentDate)
    }
    fun getImg(currentDate : String) {
        imgUiState = diaryRepository.getImg(currentDate = currentDate)
    }


}
