package eu.tutorial.moodle.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

interface DiaryRepository{
    suspend fun insertDiary(diary: Diary) : Long
    suspend fun updateDiary(diary: Diary)
    suspend fun insertActivity(activity: Activity)

    suspend fun insertPlace(place: Place)
    suspend fun insertPeople(people: People)
    fun getDiaries( currentDate : String ) : List<Diary>

}