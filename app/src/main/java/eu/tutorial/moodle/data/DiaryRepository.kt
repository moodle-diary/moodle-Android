package eu.tutorial.moodle.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

interface DiaryRepository{
    suspend fun insertDiary(diary: Diary) : Long
    suspend fun insertCause(cause: Cause)
    suspend fun insertPlace(place: Place)
    suspend fun insertComment(comment: Comment)
    suspend fun updateDiary(diary: Diary)
    fun getDiaries( currentDate : String ) : List<DiaryDto>
    fun getCauses( currentDate: String ) : List<CauseDto>
    fun getPlaces( currentDate: String ) : List<PlaceDto>
    fun getCauseGrade(targetMonth: String) : List <CauseGrade>
    fun getPlaceGrade(targetMonth: String) : List <PlaceGrade>
    fun getComments(commentDate :String) : List<CommentDto>
}