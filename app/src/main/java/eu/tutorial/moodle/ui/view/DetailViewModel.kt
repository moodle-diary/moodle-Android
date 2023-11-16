package eu.tutorial.moodle.ui.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.tutorial.moodle.data.CauseTypeDto
import eu.tutorial.moodle.data.DiaryDto
import eu.tutorial.moodle.data.DiaryRepository
import eu.tutorial.moodle.data.IconDto
import eu.tutorial.moodle.data.PlaceTypeDto

class DetailViewModel(private val diaryRepository: DiaryRepository) : ViewModel() {
    var diaryUiState by mutableStateOf(emptyList<DiaryDto>())
        private set
    var emotionUiState by mutableStateOf(emptyList<IconDto>())
        private set
    var causesUiState by mutableStateOf(emptyList<IconDto>())
        private set

    var placesUiState by mutableStateOf(emptyList<IconDto>())
        private set

    var causeTypes by mutableStateOf(emptyList<CauseTypeDto>())
        private set

    var placesTypes by mutableStateOf(emptyList<PlaceTypeDto>())
        private set

    fun getCauseTypes() {
        causeTypes = diaryRepository.getCauseTypes()
    }

    fun getPlaceTypes() {
        placesTypes = diaryRepository.getPlaceTypes()
    }

    fun getEmotions(currentDate: String) {
        emotionUiState = diaryRepository.getEmotions(currentDate = currentDate)
    }

    fun getDiaries(selectedDate: String) {
        diaryUiState = diaryRepository.getDiaries(currentDate = selectedDate)
    }

    fun getCauses(selectedDate: String) {
        causesUiState = diaryRepository.getCauses(currentDate = selectedDate)
    }

    fun getPlaces(selectedDate: String) {
        placesUiState = diaryRepository.getPlaces(currentDate = selectedDate)
    }

}