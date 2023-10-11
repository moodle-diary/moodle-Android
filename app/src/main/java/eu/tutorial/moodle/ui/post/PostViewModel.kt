package eu.tutorial.moodle.ui.post

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.tutorial.moodle.data.Activity
import eu.tutorial.moodle.data.Diary
import eu.tutorial.moodle.data.DiaryActivityCrossRef
import eu.tutorial.moodle.data.DiaryRepository
import eu.tutorial.moodle.data.People
import eu.tutorial.moodle.data.Place

class PostViewModel(private val diaryRepository: DiaryRepository) : ViewModel() {

    var diaryUiState by mutableStateOf(DiaryUiState())
        private set

    var placeUiState by mutableStateOf(PlaceUiState())
        private set

    var peopleUiState by mutableStateOf(PeopleUiState())
        private set

    fun updateDiaryUiState(diaryDetails: DiaryDetails) {
        diaryUiState =
            DiaryUiState(diaryDetails = diaryDetails, isEntryValid = true)
    }
    fun updatePlaceUiState(placeDetails: PlaceDetails) {
        placeUiState =
            PlaceUiState(placeDetails = placeDetails, isEntryValid = true)
    }
    fun updatePeopleUiState(peopleDetails: PeopleDetails) {
        peopleUiState =
            PeopleUiState(peopleDetails = peopleDetails, isEntryValid = true)
    }
    //
    private fun validateInput(diaryDetails: DiaryDetails = diaryUiState.diaryDetails): Boolean {
        TODO("Not yet implemented")
        return true
    }
 //
    suspend fun saveDiary() : Long {

        val diaryKey = diaryRepository.insertDiary(diaryUiState.diaryDetails.toDiary())
        Log.d("whatIsThis", diaryKey.toString())

        return diaryKey
    }

    suspend fun saveActivity( description : String, diaryId : Long) {
        diaryRepository.insertActivity(
            Activity(activityDescription = description, diaryId = diaryId)
        )
    }

    suspend fun savePlace() {
        diaryRepository.insertPlace(placeUiState.placeDetails.toPlace())
    }

    suspend fun savePeople() {
        diaryRepository.insertPeople(peopleUiState.peopleDetails.toPeople())
    }
}
 //
data class DiaryUiState(
    val diaryDetails: DiaryDetails = DiaryDetails(),
    val isEntryValid: Boolean = false
)

data class PlaceUiState(
    val placeDetails: PlaceDetails = PlaceDetails(),
    val isEntryValid: Boolean = false
)

data class PeopleUiState(
    val peopleDetails: PeopleDetails = PeopleDetails(),
    val isEntryValid: Boolean = false
)
 //
data class DiaryDetails(
    val currentDate : String = "",
    val emotions : Int = 0,
    val diaryText : String = "",
)
data class PlaceDetails(
    val placeId : Int = 0,
    val placeDescription : String = "",
)

data class PeopleDetails(
    val peopleId : Int = 0,
    val peopleDescription : String = "",
)
 //
fun DiaryDetails.toDiary(): Diary = Diary(
    currentDate = currentDate,
    emotions = emotions,
    diaryText = diaryText,
)
fun PlaceDetails.toPlace() : Place = Place(
    placeDescription = placeDescription,
)

fun PeopleDetails.toPeople() : People = People(
    peopleDescription = peopleDescription,
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
