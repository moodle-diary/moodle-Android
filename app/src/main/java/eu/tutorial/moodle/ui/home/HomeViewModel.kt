package eu.tutorial.moodle.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.tutorial.moodle.data.Diary
import eu.tutorial.moodle.data.DiaryDto
import eu.tutorial.moodle.data.DiaryRepository

class HomeViewModel( private val diaryRepository: DiaryRepository ) : ViewModel(){
    var diaryUiState by mutableStateOf(emptyList<DiaryDto>())
        private set

    fun getDiaries(currentDate : String) {
        diaryUiState = diaryRepository.getDiaries(currentDate = currentDate)
    }

}
