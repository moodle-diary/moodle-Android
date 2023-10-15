package eu.tutorial.moodle.ui.post

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.tutorial.moodle.data.Activity
import eu.tutorial.moodle.data.Diary
import eu.tutorial.moodle.data.DiaryRepository
import eu.tutorial.moodle.data.Food
import eu.tutorial.moodle.data.Img
import eu.tutorial.moodle.data.People
import eu.tutorial.moodle.data.Place

class PostViewModel(private val diaryRepository: DiaryRepository) : ViewModel() {

    var diaryUiState by mutableStateOf(DiaryUiState())
        private set
    // TODO have to impl valid
    fun updateDiaryUiState(diaryDetails: DiaryDetails) {
        diaryUiState =
            DiaryUiState(diaryDetails = diaryDetails, isEntryValid = true)
    }
    //
    private fun validateInput(diaryDetails: DiaryDetails = diaryUiState.diaryDetails): Boolean {
        TODO("Not yet implemented")
        return true
    }

    //
    suspend fun saveDiary(): Long {
        return diaryRepository.insertDiary(diaryUiState.diaryDetails.toDiary())
    }

    suspend fun saveActivity( description : String, diaryId : Long) {
        diaryRepository.insertActivity(
            Activity(activityDescription = description, diaryId = diaryId)
        )
    }

    suspend fun savePlace( description : String, diaryId : Long )  {
        diaryRepository.insertPlace(
            Place(placeDescription = description, diaryId = diaryId)
        )
    }

    suspend fun savePeople(description : String, diaryId : Long) {
        diaryRepository.insertPeople(
            People(peopleDescription = description, diaryId = diaryId)
        )
    }

    suspend fun saveFood(description: String, diaryId : Long) {
        diaryRepository.insertFood(
            Food(foodDescription = description, diaryId = diaryId)
        )
    }

    suspend fun saveImg(uri : Uri?, diaryId: Long) {
        diaryRepository.insertImg(
            Img(imgUri = uri.toString(), diaryId = diaryId)
        )
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
)
fun DiaryDetails.toDiary(): Diary = Diary(
    currentDate = currentDate,
    emotions = emotions,
    diaryText = diaryText,
)

fun Diary.toDiaryUiState(isEntryValid: Boolean = false): DiaryUiState = DiaryUiState(
    diaryDetails = this.toDiaryDetails(),
    isEntryValid = isEntryValid
)

fun Diary.toDiaryDetails(): DiaryDetails = DiaryDetails(
    currentDate = currentDate,
    emotions = emotions,
    diaryText = diaryText,
)
