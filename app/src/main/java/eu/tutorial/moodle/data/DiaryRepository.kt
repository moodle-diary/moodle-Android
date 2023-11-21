package eu.tutorial.moodle.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

interface DiaryRepository {
    suspend fun insertDiary(diary: Diary): Long
    suspend fun insertEmotion(emotions: Emotions)
    suspend fun insertCause(cause: Cause)
    suspend fun insertPlace(place: Place)
    suspend fun insertComment(comment: Comment)
    suspend fun insertCauseType(causeType: CauseType)
    suspend fun insertPlaceType(placeType: PlaceType)
    suspend fun updateDiary(diary: Diary)
    fun getDiaries(currentDate: String): List<DiaryDto>
    fun getEmotions(currentDate: String): List<IconDto>
    fun getCauses(currentDate: String): List<IconDto>
    fun getPlaces(currentDate: String): List<IconDto>
    fun getEmotionGrade(targetMonth: String): List<EmotionGrade>
    fun getCauseGrade(targetMonth: String): List<CauseGrade>
    fun getPlaceGrade(targetMonth: String): List<PlaceGrade>
    fun getCauseTypes(): List<CauseTypeDto>
    fun getPlaceTypes(): List<PlaceTypeDto>
    fun getComments(commentDate: String): List<CommentDto>
    fun getGreatDays(targetMonth: String): Int
}