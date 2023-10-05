package eu.tutorial.moodle.ui.post

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.tutorial.moodle.data.Diary
import eu.tutorial.moodle.data.DiaryRepository
import eu.tutorial.moodle.data.Emoji

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
    val emotions : Int = 0,
    val diaryText : String = "",
    val emojis : List<Emoji> = emptyList()
)

fun DiaryDetails.toDiary(): Diary = Diary(
    currentDate = currentDate,
    emotions = emotions,
    diaryText = diaryText,
    emojis = emojis,
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
    emotions = emotions,
    diaryText = diaryText,
    emojis = emojis
)
