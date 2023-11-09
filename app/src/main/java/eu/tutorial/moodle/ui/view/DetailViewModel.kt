package eu.tutorial.moodle.ui.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.tutorial.moodle.data.DiaryDto
import eu.tutorial.moodle.data.DiaryRepository
import eu.tutorial.moodle.data.IconDto

class DetailViewModel(private val diaryRepository: DiaryRepository) : ViewModel() {
    var diaryUiState by mutableStateOf(emptyList<DiaryDto>())
        private set

    var causesUiState by mutableStateOf(emptyList<IconDto>())
        private set

    var placesUiState by mutableStateOf(emptyList<IconDto>())
        private set

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