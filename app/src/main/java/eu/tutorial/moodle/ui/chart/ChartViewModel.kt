package eu.tutorial.moodle.ui.chart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.tutorial.moodle.data.CauseGrade
import eu.tutorial.moodle.data.CauseTypeDto
import eu.tutorial.moodle.data.DescriptionDto
import eu.tutorial.moodle.data.DiaryRepository
import eu.tutorial.moodle.data.EmotionGrade
import eu.tutorial.moodle.data.PlaceGrade
import eu.tutorial.moodle.data.PlaceTypeDto

class ChartViewModel(private val diaryRepository: DiaryRepository) : ViewModel() {

    var emotionList by mutableStateOf(emptyList<DescriptionDto>())
    var causeList by mutableStateOf(emptyList<DescriptionDto>())
    var placeList by mutableStateOf(emptyList<DescriptionDto>())

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


    fun getEmotionList(targetMonth: String) {
        emotionList = diaryRepository.getEmotionGrade("$targetMonth%").map { it.toDesDto() }
    }

    fun getActList(targetMonth: String) {
        causeList = diaryRepository.getCauseGrade("$targetMonth%").map { it.toDesDto() }
    }

    fun getPlaceList(targetMonth: String) {
        placeList = diaryRepository.getPlaceGrade("$targetMonth%").map { it.toDesDto() }
    }
}

fun EmotionGrade.toDesDto(): DescriptionDto = DescriptionDto(
    description = emotion,
    cnt = cnt
)

fun CauseGrade.toDesDto(): DescriptionDto = DescriptionDto(
    description = cause,
    cnt = cnt,
)

fun PlaceGrade.toDesDto(): DescriptionDto = DescriptionDto(
    description = placeDescription,
    cnt = cnt,
)