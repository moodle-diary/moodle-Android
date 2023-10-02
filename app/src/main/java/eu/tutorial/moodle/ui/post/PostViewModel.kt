package eu.tutorial.moodle.ui.post

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.tutorial.moodle.data.Diary
import eu.tutorial.moodle.data.DiaryRepository

class PostViewModel(private val diaryRepository: DiaryRepository) : ViewModel() {

    var diaryUiState by mutableStateOf(DiaryUiState())
        private set // ?

    fun updateUiState(diaryDetails: DiaryDetails) {
        diaryUiState =
            DiaryUiState(diaryDetails = diaryDetails, isEntryValid = true)
    }

    private fun validateInput(diaryDetails: DiaryDetails = diaryUiState.diaryDetails): Boolean {
        TODO("Not yet implemented")
        return true
    }

    suspend fun saveItem() {
        diaryRepository.insertDiary(diaryUiState.diaryDetails.toDiary())
    }

}

data class DiaryUiState(
    val diaryDetails: DiaryDetails = DiaryDetails(),
    val isEntryValid: Boolean = false
)


data class DiaryDetails(
    val currentDate : String = "",
    val diaryText : String = "",
)

fun DiaryDetails.toDiary(): Diary = Diary(
    currentDate = currentDate,
    diaryText = diaryText
)

/**
 * Extension function to convert [Item] to [ItemUiState]
 */
fun Diary.toDiaryUiState(isEntryValid: Boolean = false): DiaryUiState = DiaryUiState(
    diaryDetails = this.toDiaryDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Item] to [ItemDetails]
 */
fun Diary.toDiaryDetails(): DiaryDetails = DiaryDetails(
    currentDate = currentDate,
    diaryText = diaryText,
)
