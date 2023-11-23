package eu.tutorial.moodle.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.tutorial.moodle.data.CauseTypeDto
import eu.tutorial.moodle.data.DiaryDto
import eu.tutorial.moodle.data.DiaryRepository
import eu.tutorial.moodle.data.IconDto
import eu.tutorial.moodle.data.PlaceTypeDto

class HomeViewModel(private val diaryRepository: DiaryRepository) : ViewModel() {
    var diaryUiState by mutableStateOf(emptyList<DiaryDto>())
        private set
    var emotionUiState by mutableStateOf(emptyList<IconDto>())
        private set
    var causeUiState by mutableStateOf(emptyList<IconDto>())
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

    fun getDiaries(currentDate: String) {
        diaryUiState = diaryRepository.getDiaries(currentDate = currentDate)
    }

    fun getEmotions(currentDate: String) {
        emotionUiState = diaryRepository.getEmotions(currentDate = currentDate)
    }

    fun getCauses(currentDate: String) {
        causeUiState = diaryRepository.getCauses(currentDate = currentDate)
    }

    fun getPlaces(currentDate: String) {
        placesUiState = diaryRepository.getPlaces(currentDate = currentDate)
    }
}
