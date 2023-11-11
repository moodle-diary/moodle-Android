package eu.tutorial.moodle.ui.post

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.tutorial.moodle.data.Cause
import eu.tutorial.moodle.data.CauseType
import eu.tutorial.moodle.data.CauseTypeDto
import eu.tutorial.moodle.data.Diary
import eu.tutorial.moodle.data.DiaryRepository
import eu.tutorial.moodle.data.Emotions
import eu.tutorial.moodle.data.Place
import eu.tutorial.moodle.data.PlaceType
import eu.tutorial.moodle.data.PlaceTypeDto

class PostViewModel(private val diaryRepository: DiaryRepository) : ViewModel() {

    var diaryUiState by mutableStateOf(DiaryUiState())
        private set

    // TODO have to impl valid
    var showDialog by mutableStateOf(false)
    var isCancel by mutableStateOf(false)

    var causeTypes by mutableStateOf(emptyList<CauseTypeDto>())
        private set

    var placesTypes by mutableStateOf(emptyList<PlaceTypeDto>())
        private set

    fun updateDiaryUiState(diaryDetails: DiaryDetails) {
        diaryUiState =
            DiaryUiState(diaryDetails = diaryDetails, isEntryValid = true)
    }

    private fun validateInput(diaryDetails: DiaryDetails = diaryUiState.diaryDetails): Boolean {
        TODO("Not yet implemented")
        return true
    }

    fun getCauseTypes() {
        causeTypes = diaryRepository.getCauseTypes()
    }

    fun getPlaceTypes() {
        placesTypes = diaryRepository.getPlaceTypes()
    }

    suspend fun saveDiary(): Long {
        return diaryRepository.insertDiary(diaryUiState.diaryDetails.toDiary())
    }

    suspend fun saveEmotions(description: String, diaryId: Long) {
        diaryRepository.insertEmotion(
            Emotions(
                diaryId = diaryId,
                emotion = description
            )
        )
    }

    suspend fun saveCause(description: String, diaryId: Long) {
        diaryRepository.insertCause(
            Cause(cause = description, diaryId = diaryId)
        )
    }

    suspend fun savePlace(description: String, diaryId: Long) {
        diaryRepository.insertPlace(
            Place(placeDescription = description, diaryId = diaryId)
        )
    }

    suspend fun saveCauseType(causeType: String) {
        diaryRepository.insertCauseType(
            CauseType(causeType = causeType)
        )
    }

    suspend fun savePlaceType(placeType: String) {
        diaryRepository.insertPlaceType(
            PlaceType(placeType = placeType)
        )
    }
}

data class DiaryUiState(
    val diaryDetails: DiaryDetails = DiaryDetails(),
    val isEntryValid: Boolean = false
)

data class DiaryDetails(
    val currentDate: String = "",
    val diaryText: String = "",
    val thought: String = ""
)

fun DiaryDetails.toDiary(): Diary = Diary(
    currentDate = currentDate,
    diaryText = diaryText,
    thought = thought,
)

fun Diary.toDiaryUiState(isEntryValid: Boolean = false): DiaryUiState = DiaryUiState(
    diaryDetails = this.toDiaryDetails(),
    isEntryValid = isEntryValid
)

fun Diary.toDiaryDetails(): DiaryDetails = DiaryDetails(
    currentDate = currentDate,
    diaryText = diaryText,
    thought = thought,
)
