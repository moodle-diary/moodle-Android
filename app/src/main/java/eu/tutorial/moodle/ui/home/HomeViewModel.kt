package eu.tutorial.moodle.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.tutorial.moodle.data.CauseDto
import eu.tutorial.moodle.data.Diary
import eu.tutorial.moodle.data.DiaryDto
import eu.tutorial.moodle.data.DiaryRepository
import eu.tutorial.moodle.data.PlaceDto

class HomeViewModel( private val diaryRepository: DiaryRepository ) : ViewModel(){
    var diaryUiState by mutableStateOf(emptyList<DiaryDto>())
        private set

    var causeUiState by mutableStateOf(emptyList<CauseDto>())
        private set

    var placesUiState by mutableStateOf(emptyList<PlaceDto>())
        private set

   fun getDiaries(currentDate : String) {
        diaryUiState = diaryRepository.getDiaries(currentDate = currentDate)
    }
    fun getCauses(currentDate : String) {
        causeUiState = diaryRepository.getCauses(currentDate = currentDate)
    }
    fun getPlaces(currentDate : String) {
        placesUiState = diaryRepository.getPlaces(currentDate = currentDate)
    }
}
