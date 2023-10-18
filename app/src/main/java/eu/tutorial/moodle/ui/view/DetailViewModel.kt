package eu.tutorial.moodle.ui.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.tutorial.moodle.data.ActivityDto
import eu.tutorial.moodle.data.DiaryDto
import eu.tutorial.moodle.data.DiaryRepository
import eu.tutorial.moodle.data.FoodDto
import eu.tutorial.moodle.data.ImgDto
import eu.tutorial.moodle.data.PeopleDto
import eu.tutorial.moodle.data.PlaceDto

class DetailViewModel (private val diaryRepository: DiaryRepository) : ViewModel(){
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

    fun getDiaries(selectedDate : String) {
        diaryUiState = diaryRepository.getDiaries(currentDate = selectedDate)
    }
    fun getActivities(selectedDate : String) {
        activitiesUiState = diaryRepository.getActivities(currentDate = selectedDate)
    }
    fun getPlaces(selectedDate : String) {
        placesUiState = diaryRepository.getPlaces(currentDate = selectedDate)
    }
    fun getPeople(selectedDate : String) {
        peopleUiState = diaryRepository.getPeople(currentDate = selectedDate)
    }
    fun getFoods(selectedDate : String) {
        foodsUiState = diaryRepository.getFood(currentDate = selectedDate)
    }
    fun getImg(selectedDate : String) {
        imgUiState = diaryRepository.getImg(currentDate = selectedDate)
    }

}